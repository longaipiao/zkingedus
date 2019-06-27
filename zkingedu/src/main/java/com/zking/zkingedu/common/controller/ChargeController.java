package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.ChargeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChargeController
 * @Author likai
 **/
@Controller
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private Charge charge;


    //后台充值记录
    @RequestMapping("/chargeURL")
    String chargeURL(){
        return "admin/likai/chargesShow";
    }

    //跳转个人中心
    @RequestMapping("/userinfo")
    String userInfo(HttpServletRequest request){
        return "user/userinfo";
    }

    /**
     * 后台chargesShow.html绑定表格数据
     */
    @ResponseBody
    @RequestMapping("/houtai/getCharges")
    Map<String,Object> getCharges(@Param("page") Integer page, @Param("limit") Integer limit,Charge charge){
        Map<String,Object> chargesMap = new HashMap<>();

        //分页
        Page pageLine = PageHelper.startPage(page, limit);

        List<Map<String,Object>> ls = new ArrayList<>();

        List<Charge> charges = chargeService.getCharges(charge);
        for (Charge charge1 : charges) {
            //new一个map集合用来对应前台layui不能多层嵌套问题，取出值放在同一层
            Map<String,Object> maps = new HashMap<>();
            maps.put("chargeID",charge1.getChargeID());
            maps.put("chargeIntegral",charge1.getChargeIntegral());
            maps.put("chargeMoney",charge1.getChargeMoney());
            maps.put("chargeTime",charge1.getChargeTime());
            maps.put("userName",charge1.getUser().getUserName());
            ls.add(maps);
        }

        chargesMap.put("count",pageLine.getTotal());
        chargesMap.put("code",0);
        chargesMap.put("msg","绑定数据");
        chargesMap.put("data",ls);

        return chargesMap;
    }

    /**
     * @ResponseBody  处理前台404错误。Spring框架问题
     * 根据线路ID删除线路和线路基本信息
     * @param request
     */
    @ResponseBody
    @RequestMapping("/delCharge")
    void delLine(HttpServletRequest  request){
        String chargeID = request.getParameter("chargeID");
        //删除充值记录
        chargeService.delChargeByID(chargeID);
    }

    /**
     * 个人中心查看自己的充值记录
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/MyCharges")
    Map<String,Object> getMyCharges(HttpServletRequest  request){
        //User user = (User) request.getSession().getAttribute("user");
        //user.getUserID

        String page = request.getParameter("page");
        String limit = request.getParameter("pageSize");
        //分页
        Page pageLine = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));

        List<Charge> myCharges = chargeService.getChargesByUserID(36);
        Map<String,Object> chargeMap = new HashMap<>();
        chargeMap.put("count",pageLine.getTotal());
        chargeMap.put("code","");
        chargeMap.put("msg","");
        chargeMap.put("data",myCharges);

        return chargeMap;
    }
}
