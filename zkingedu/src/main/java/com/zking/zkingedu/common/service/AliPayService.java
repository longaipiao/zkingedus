package com.zking.zkingedu.common.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zking.zkingedu.common.config.AlipayConfig;
import com.zking.zkingedu.common.model.AlipayBean;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName AliPayService
 * @Author likai
 **/
@Service
@Slf4j
public class AliPayService {


    @Resource
    private BillService billService;

    @Resource
    private UserService userService;

    @Resource
    private ChargeService chargeService;

    @Resource
    private Charge charge;

    @Resource
    private Bill bill;

    // 支付宝支付
    public String alipay(String outTradeNo, String subject, String totalAmount, String body, String notifyUrl, HttpServletRequest request, HttpServletResponse response) {
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        // 1、获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        String form = "";
        try {

            //得到session得到用户的所有信息
            User sessionUser = (User)request.getSession().getAttribute("user");
            //从DB获取用户信息
            User user = userService.getUserByid(sessionUser.getUserID());


            charge.setChargeMoney(Integer.parseInt(totalAmount));//充值金额
            charge.setChargeIntegral(Integer.parseInt(body));//积分
            charge.setChargeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//充值时间
            charge.setChargeUid(user.getUserID());//用户ID
            chargeService.insertCharge(charge);
            log.info("================================充值记录成功================================");

            //增加账单表的信息
            bill.setBillIntegral(Integer.parseInt(body));//积分
            bill.setBillType(0);//充值类型为0：收入
            bill.setBillTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//生成账单时间
            bill.setBillContent(subject);//账单内容
            bill.setBillUid(user.getUserID());//用户ID
            billService.insertBill(bill);
            log.info("================================充值账单成功================================");

            //用户增加积分
            userService.addIntegral(user.getUserID(),body);
            log.info("================================用户增加积分成功================================");



            // 调用SDK生成表单
            response.reset();
            // 发起支付且成功以后会返回一个支付宝收银台页面的地址,按照官方demo直接到页面即可
            form = alipayClient.pageExecute(alipayRequest).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.charset);
            response.getOutputStream().write(form.getBytes());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回付款信息
        return form;
    }

}