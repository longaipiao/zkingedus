package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Order;

import java.util.List;

/**
 * Order服务接口
 */
public interface OrderService {
    /**
     * 增加一条订单信息
     * @param order
     * @return
     */
    int insertOrder(Order order);

    /**
     * 根据用户ID得到用户购买的所有课程
     * @param orderUid
     * @return
     */
    Integer getOrderCidByUserID(Integer orderUid,Integer orderCid);

    /**
     * 根据用户iD得到自己所有订单
     * @param orderUid
     * @return
     */
    List<Order> getMyOrdersByUserID(Integer orderUid);

    /**
     * 后台订单管理  orderShow.html
     * @param order
     * @return
     */
    List<Order> getOrders(Order order);

    /**
     * 根据ID删除订单
     * @param orderID
     * @return
     */
    int delOrderByID(String orderID);

    /**
     * userinfo.html 我的课程
     * @param userID    用户id
     * @return
     */
    List<Course> getMyCourses(Integer userID);
}
