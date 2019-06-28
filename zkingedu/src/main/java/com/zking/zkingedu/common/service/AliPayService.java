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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AliPayService {

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private BillService billService;

    @Autowired
    private UserService userService;

    @Autowired
    private Charge charge;

    @Autowired
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

            //充值金额
            charge.setChargeMoney(Integer.parseInt(totalAmount));
            //积分
            charge.setChargeIntegral(Integer.parseInt(body));
            //充值时间
            charge.setChargeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            //最后调试后修改正常数据
            /*User user = (User) request.getSession().getAttribute("user");
            charge.setChargeUid(user.getUserID());*/

            //测试数据   用户ID
            charge.setChargeUid(36);
            int i = chargeService.insertCharge(charge);
            if(i==0){
                return "user/userInfo";
            } else {
                //增加账单表的信息
                bill.setBillIntegral(Integer.parseInt(body));//积分
                bill.setBillType(1);//充值类型为1：收入
                bill.setBillTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//生成账单时间
                bill.setBillContent(subject);//账单内容
                //用户ID  后期修改
                //bill.setBillUid(user.getUserID());
                bill.setBillUid(36);//用户ID   测试
                billService.insertBill(bill);

                //后期修改
                //userService.addIntegral(user.getUserID,body);
                //用户增加积分
                userService.addIntegral(36,body);
            }


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