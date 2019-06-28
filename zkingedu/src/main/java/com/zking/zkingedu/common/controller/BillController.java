package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Map<String,Object> getMyBills(HttpServletRequest request){
        //后期修改
        User user = (User) request.getSession().getAttribute("user");

        //分页
        Page pageLine = PageHelper.startPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("pageSize")));
        List<Bill> bills = billService.myBills(user.getUserID());

        Map<String,Object> maps = new HashMap<>();
        maps.put("count",pageLine.getTotal());
        maps.put("code","");
        maps.put("msg","");
        maps.put("data",bills);
        return maps;
    }
}
