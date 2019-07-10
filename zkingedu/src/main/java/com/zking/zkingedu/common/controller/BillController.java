package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.BillService;
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
 * @ClassName BillController
 * @Author likai
 **/
@Controller
@Slf4j
@Transactional
public class BillController {

    @Resource
    private BillService billService;

    @Resource
    private User user;

    @RequestMapping("/admin/billURL")
    String billURL(){
        return "admin/likai/billShow";
    }

    /**
     * userinfo.html 个人中心的账单记录
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/MyBills")
    Map<String,Object> getMyBills(HttpServletRequest request){
        //得到user对象
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


    /**
     * billShow.html 页面数据绑定及查询
     * @param page
     * @param limit
     * @param search
     * @param type
     * @param bill
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/billAll")
    Map<String,Object> getOrdersAll(@Param("page")Integer page, @Param("limit")Integer limit, @Param("search") String search, @Param("type") String type,Bill bill){
        Map<String,Object> billMaps = new HashMap<>();

        log.info("类型："+type+"；文本框的值："+search);

        try {
            if(search!=null){
                if(("用户名").equals(type)){
                    user.setUserName(search);
                    bill.setUser(user);
                } else if("账单编号".equals(type)){
                    bill.setBillID(Integer.parseInt(search));
                } else if("账单积分".equals(type)){
                    bill.setBillIntegral(Integer.parseInt(search));
                } else if ("账单类型".equals(type)) {
                    if(search.equals("收")||search.equals("入")){
                        bill.setBillType(0);
                    }else if(search.equals("支")||search.equals("出")){
                        bill.setBillType(1);
                    }
                } else if("账单时间".equals(type)){
                    bill.setBillTime(search);
                } else if("账单详细".equals(type)){
                    bill.setBillContent(search);
                }
            }
        } catch (NumberFormatException e) {
            search = null;
        }

        //开启分页
        Page billPage = PageHelper.startPage(page, limit);

        //
        List<Bill> bills = billService.getAdminBills(bill);
        List<Map<String,Object>> ls = new ArrayList<>();
        String jsonType = "";
        for (Bill bill1 : bills) {

            if(bill1.getBillType()==0){
                jsonType="收入";
            } else if(bill1.getBillType()==1){
                jsonType="支出";
            }
            Map<String,Object> maps = new HashMap<>();
            maps.put("billID",bill1.getBillID());
            maps.put("billIntegral",bill1.getBillIntegral());
            maps.put("billMoney",bill1.getBillIntegral()/10);
            maps.put("billTime",bill1.getBillTime());
            maps.put("userName",bill1.getUser().getUserName());
            maps.put("billContent",bill1.getBillContent());
            maps.put("billType",jsonType);
            ls.add(maps);
        }

        billMaps.put("count",billPage.getTotal());
        billMaps.put("code","");
        billMaps.put("msg","");
        billMaps.put("data",ls);

        return billMaps;
    }

    /**
     * 删除账单记录  billShow.html
     * @param billID
     */
    @ResponseBody
    @RequestMapping("/admin/delbill")
    void delBillI(@Param("billID") Integer billID){
        billService.delBill(billID);
    }


    /**
     * 饼图
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/billTypeSum")
    Map<String,Object> billTypeSunm(){
        List<Bill> bills = billService.sumGroupByBillType();
        Map<String,Object> maps = new HashMap<>();

        return maps;
    }
}
