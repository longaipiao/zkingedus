package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.zking.zkingedu.common.model.User;

/**
 * 用户 服务层
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 查询数据库是否存在该手机号用户
     * @param phone
     * @return
     */
    String queryPhone(String phone);

    /**
     * 用户登陆
     * @param user
     * @return
     */
    User userLogin(@Param("user") User user);

    /**
     * 每次登陆修改ip地址及最近一次登陆时间
     * @param address
     * @param lastTime
     * @return
     */
    int updateAddressAndTime(@Param("address") String address,@Param("lastTime") String lastTime,@Param("uid") Integer uid);

    /**
     * 根据id获取用户
     * @param uid
     * @return
     */
    User getUserByid(Integer uid);

    /**
     * 根据电话获取用户
     * @param phone
     * @return
     */
    User getUserByName(String phone);

    /**
     * 添加qq用户
     * @param user
     * @return
     */
    int addQqLogin(User user);

    /**
     * @author likai
     *      用户充值增加积分
     * @param userID
     * @param userIntegrsl
     * @return
     */
    int addIntegral(Integer userID,String userIntegrsl);

}
