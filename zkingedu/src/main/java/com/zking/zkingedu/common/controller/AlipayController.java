package com.zking.zkingedu.common.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.zking.zkingedu.common.config.AlipayConfig;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.AliPayService;
import com.zking.zkingedu.common.service.BillService;
import com.zking.zkingedu.common.service.ChargeService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.PayUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @ClassName AlipayController
 * @Author likai
 **/
@Controller
@Slf4j
@Transactional
public class AlipayController {

    @Resource
    private AliPayService aliPayService;
    @Resource
    private ChargeService chargeService;
    @Resource
    private UserService userService;
    @Resource
    private BillService billService;
    @Resource
    private Charge charge;
    @Resource
    private Bill bill;


    @RequestMapping("/charge")
    String chargeURL(){
        return "/userInfo/charge";
    }


    /**
     *
     * @param outTradeNo 订单号
     * @param subject  商品名称
     * @param totalAmount  付款金额
     * @param body   商品描述
     * @return String
     */
    @RequestMapping(value = "/pay")
    public String aliPay(String outTradeNo, String subject, String totalAmount, String body, HttpServletRequest request , HttpServletResponse response){
        // 为防止订单号重否 此处模拟生成唯一订单号
        outTradeNo = PayUtils.createUnilCode();
        subject = "充值积分";
        //支付宝支付
        return aliPayService.alipay(outTradeNo,subject,totalAmount.toString(),body,AlipayConfig.notify_url,request,response);
    }

    /**
     * 支付宝异步回调
     */
    /*@GetMapping(value = "/alipay/orderNotify")
    public String orderNotify(HttpServletRequest request) throws Exception {
        log.info("回调成功");

        User user = (User) request.getSession().getAttribute("user");

        //接收的金额
        String total_amount = request.getParameter("total_amount");
        //金额
        int money = Integer.parseInt(total_amount.substring(0, total_amount.lastIndexOf(".")));

        //积分
        int integral = money*10;

        charge.setChargeMoney(money);//充值金额
        charge.setChargeIntegral(integral);//积分
        charge.setChargeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//充值时间
        charge.setChargeUid(user.getUserID());//用户ID
        log.info("================================充值记录添加成功================================");

        //增加账单表的信息
        bill.setBillIntegral(integral);//积分
        bill.setBillType(0);//充值类型为0：收入
        bill.setBillTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//生成账单时间
        bill.setBillContent("充值积分");//账单内容
        bill.setBillUid(user.getUserID());//用户ID
        billService.insertBill(bill);
        log.info("================================充值积分成功================================");

        //用户增加积分
        userService.addIntegral(user.getUserID(), "+integral+");
        return "redirect:user/userInfo.html";

    }*/
}
