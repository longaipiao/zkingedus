package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Bill;
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
//      User user = (User) request.getSession().getAttribute("user");
//      List<Bill> bills = billService.myBills(user.getUserID());
        String page = request.getParameter("page");
        String limit = request.getParameter("pageSize");
        //分页
        Page pageLine = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));

        //测试
        List<Bill> bills = billService.myBills(36);
        Map<String,Object> maps = new HashMap<>();
        maps.put("count",pageLine.getTotal());
        maps.put("code","");
        maps.put("msg","");
        maps.put("data",bills);
        return maps;
    }
}
