package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName BillController
 * @Author likai
 **/
@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @ResponseBody
    @RequestMapping("/user/MyBills")
    List<Bill> getMyBills(HttpServletRequest request){
        //后期修改
//      User user = (User) request.getSession().getAttribute("user");
//      List<Bill> bills = billService.myBills(user.getUserID());

        //测试
        List<Bill> bills = billService.myBills(36);
        return bills;
    }
}
