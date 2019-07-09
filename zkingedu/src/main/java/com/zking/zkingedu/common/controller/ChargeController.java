package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.model.Message;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.ChargeService;
import com.zking.zkingedu.common.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
@Slf4j
@Transactional
public class ChargeController {

    @Resource
    private ChargeService chargeService;

    @Resource
    private Charge charge;

    @Resource
    private User user;

    @Resource
    private MessageService messageService;


    //后台充值记录
    @RequestMapping("/admin/chargeURL")
    String chargeURL(){
        return "admin/likai/chargesShow";
    }



    /**
     * 后台chargesShow.html绑定表格数据
     */
    @ResponseBody
    @RequestMapping("/admin/getCharges")
    Map<String,Object> getCharges(@Param("page") Integer page, @Param("limit") Integer limit,@Param("search") String search,@Param("type") String type,Charge charge){
        Map<String,Object> chargesMap = new HashMap<>();

        log.info("类型："+type+"；文本框的值："+search);


        try {
            if(search!=null){
                if(("用户名").equals(type)){
                    user.setUserName(search);
                    charge.setUser(user);
                } else if("序号".equals(type)){
                    charge.setChargeID(Integer.parseInt(search));
                } else if("充值金额".equals(type)){
                    charge.setChargeMoney(Integer.parseInt(search));
                } else if ("所得积分".equals(type)) {
                    charge.setChargeIntegral(Integer.parseInt(search));
                } else if("充值时间".equals(type)){
                    charge.setChargeTime(search);
                }
            }
        } catch (NumberFormatException e) {
            search = null;
        }

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
     * ResponseBody  处理前台404错误。Spring框架问题
     * 根据充值记录ID删除记录
     * @param chargeID
     */
    @ResponseBody
    @RequestMapping("/admin/delCharge")
    void delLine(@Param("chargeID") String chargeID  ){
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
        User user = (User) request.getSession().getAttribute("user");

        //分页
        Page pageLine = PageHelper.startPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("pageSize")));

        List<Charge> myCharges = chargeService.getChargesByUserID(user.getUserID());
        Map<String,Object> chargeMap = new HashMap<>();
        chargeMap.put("count",pageLine.getTotal());
        chargeMap.put("code","");
        chargeMap.put("msg","");
        chargeMap.put("data",myCharges);

        return chargeMap;
    }


    /**
     *
     * @param page 当前页
     * @param limit 每页多少条数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/MyMessages")
    Map<String,Object> getMyMessages(@Param("page") Integer page,  @Param("limit") Integer limit,HttpServletRequest request){
        Map<String,Object> messageMaps = new HashMap<>();
        log.info("page"+page+"__limit:"+limit);
        User user = (User) request.getSession().getAttribute("user");
        Page pageLine = PageHelper.startPage(1,3);

        List<Message> myMessages = messageService.getMyMessages(36);

        messageMaps.put("count",pageLine.getTotal());
        messageMaps.put("code","");
        messageMaps.put("msg","");
        messageMaps.put("data",myMessages);

        return messageMaps;
    }

}
