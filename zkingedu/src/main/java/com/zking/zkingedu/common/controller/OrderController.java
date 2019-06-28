package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Order;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.BillService;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.OrderService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IdGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Author likai
 **/
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private BillService billService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private Order order;

    @Autowired
    private Bill bill;
    //当前时间
    String nowDate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());

    /**
     *  个人中心 userinfo.html 我的订单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/MyOrders")
    Map<String,Object> getMyOrders(HttpServletRequest request){
        //后期修改
        User user = (User) request.getSession().getAttribute("user");

        //分页
        Page pageLine = PageHelper.startPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("pageSize")));
        List<Order> myOrders = orderService.getMyOrdersByUserID(user.getUserID());
        for (Order myOrder : myOrders) {
            myOrder.setCourse(courseService.getCourseByCourseID(myOrder.getOrderCid()));
        }

        Map<String,Object> maps = new HashMap<>();
        maps.put("count",pageLine.getTotal());
        maps.put("code","");
        maps.put("msg","");
        maps.put("data",myOrders);
        return maps;
    }



    /**
     * 购买课程  show.html  开始学习button
     * @param integral
     * @param courseID
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/buyOrder")
    String  bitBuyCourse(String integral, String courseID, HttpServletRequest request){
        System.out.println("============================>积分："+integral+"课程ID："+courseID+"<============================");

        //得到session得到用户的所有信息
        User user = (User)request.getSession().getAttribute("user");
        //用户没有登录
        if(user==null){
            return "USER_NULL";
        }
        //到订单表查到该用户已经够买的订单的课程号
        Integer orderCid = orderService.getOrderCidByUserID(user.getUserID(),Integer.parseInt(courseID));
        //如果该用户已经购买
        if (orderCid!=null) {
            System.out.println("==============================================已经购买");
            return "ALREADY_BUY";//让用户直接观看
        }
        else{//如果没有购买
            //如果用户的积分少于该课程的兑换积分
            if(user.getUserIntegrsl()<Integer.parseInt(integral)){
                System.out.println(user.getUserIntegrsl());
                return "INTEGRAL_FEW";//提示用户积分不够提醒充值
            }
            else {
                return "NOW_BUY";
            }
        }
    }


    @ResponseBody
    @RequestMapping("/order/buyCourse")
    String buyCourse(String integral, String courseID, HttpServletRequest request){

        System.out.println("============================>积分："+integral+"课程ID："+courseID+"<============================");
        //得到session得到用户的所有信息
        User user = (User)request.getSession().getAttribute("user");

        //用户积分足够   可以兑换
        //1.用户扣除积分
        userService.deducuIntegralWithUser(user.getUserID(),Integer.parseInt(integral));
        Course course = courseService.getCourseByCourseID(Integer.parseInt(courseID));
        System.out.println("================================用户扣除积分成功================================");
        //2.增加订单记录
        order.setOrderID(new IdGeneratorUtils().nextId());
        order.setOrderUid(user.getUserID());
        order.setOrderIntegral(Integer.parseInt(integral));
        order.setChargeTime(nowDate);
        order.setOrderCid(Integer.parseInt(courseID));
        orderService.insertOrder(order);
        System.out.println("================================添加订单成功================================");
        //3.增加账单记录
        bill.setBillUid(user.getUserID());
        bill.setBillType(1);
        bill.setBillTime(nowDate);
        bill.setBillIntegral(Integer.parseInt(integral));
        bill.setBillContent("购买《"+course.getCourseName()+"》,消费："+integral+"积分");
        billService.insertBill(bill);
        System.out.println("================================添加账单成功================================");
        //return "NOW_BUY";
        return "BUY_SUCCESS";
    }
}
