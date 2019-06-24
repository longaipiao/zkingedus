package com.zking.zkingedu.common.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.zking.zkingedu.common.config.AlipayConfig;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.service.AliPayService;
import com.zking.zkingedu.common.service.BillService;
import com.zking.zkingedu.common.service.ChargeService;
import com.zking.zkingedu.common.utils.PayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class AlipayController {

    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private ChargeService chargeService;

    @Autowired
    private BillService billService;

    @Autowired
    private Charge charge;

    @Autowired
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
    String integral = "";
    String money = "";
    String content = "";
    @RequestMapping(value = "/pay")
    public String aliPay(String outTradeNo, String subject, String totalAmount, String body, HttpServletRequest request , HttpServletResponse response){
        // 为防止订单号重否 此处模拟生成唯一订单号
        outTradeNo = PayUtils.createUnilCode();
        subject = "充值积分";
        integral = body;
        money = totalAmount;
        content = subject;
        //支付宝支付
        return aliPayService.alipay(outTradeNo,subject,totalAmount.toString(),body,AlipayConfig.notify_url,request,response);
    }

    /**
     * 支付宝异步回调
     */
    @GetMapping(value = "/alipay/orderNotify")
    public String orderNotify(HttpServletRequest request,  HttpServletResponse response) throws Exception {
        System.out.println("回调成功");
        //充值金额
        /*charge.setChargeMoney(Double.parseDouble(money));
        //积分
        charge.setChargeIntegral(Integer.parseInt(integral));
        //充值时间
        charge.setChargeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //最后调试后修改正常数据
            *//*User user = (User) request.getSession().getAttribute("user");
            charge.setChargeUid(user.getUserID());*//*

        //测试数据   用户ID
        charge.setChargeUid(36);
        int i = chargeService.insertCharge(charge);
        if(i==0){
            return "user/userInfo";
        } else {
            //增加账单表的信息
            bill.setBillIntegral(Integer.parseInt(integral));//积分
            bill.setBillType(1);//充值类型为1：收入
            bill.setBillTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//生成账单时间
            bill.setBillContent(content);//账单内容
            //用户ID
            //bill.setBillUid(user.getUserID());
            bill.setBillUid(36);//用户ID   测试
            billService.insertBill(bill);
        }*/
        return "user/userInfo";
    }

}
