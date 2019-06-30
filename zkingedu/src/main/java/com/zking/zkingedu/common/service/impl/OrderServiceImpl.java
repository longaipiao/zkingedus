package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.OrderDao;
import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Order;
import com.zking.zkingedu.common.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Order服务接口  实现层
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    /**
     * 增加一条订单信息
     *
     * @param order
     * @return
     */
    @Override
    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    /**
     * 根据用户ID得到用户购买的所有课程
     *
     * @param orderUid   用户ID
     * @param orderCid  订单表中的课程ID
     * @return
     */
    @Override
    public Integer getOrderCidByUserID(Integer orderUid,Integer orderCid) {
        return orderDao.getOrderCidByUserID(orderUid,orderCid);
    }

    /**
     * 根据用户iD得到自己所有订单
     *
     * @param orderUid
     * @return
     */
    @Override
    public List<Order> getMyOrdersByUserID(Integer orderUid) {
        return orderDao.getMyOrdersByUserID(orderUid);
    }

    /**
     * 后台订单管理  orderShow.html
     *
     * @param order
     * @return
     */
    @Override
    public List<Order> getOrders(Order order) {
        return orderDao.getOrders(order);
    }

    /**
     * 根据ID删除订单
     *
     * @param orderID
     * @return
     */
    @Override
    public int delOrderByID(String orderID) {
        return orderDao.delOrderByID(orderID);
    }

    /**
     * userinfo.html 我的课程
     *
     * @param userID 用户id
     * @return
     */
    @Override
    public List<Course> getMyCourses(Integer userID) {
        return orderDao.getMyCourses(userID);
    }
}
