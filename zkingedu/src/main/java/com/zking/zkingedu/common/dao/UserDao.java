package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import com.zking.zkingedu.common.model.User;

/**
 * 用户接口
 */
@Mapper
public interface UserDao {

    /**
     * @author likai
     *      用户充值增加积分
     * @param userID
     * @param userIntegrsl
     * @return
     */
    int addIntegral(Integer userID,String userIntegrsl);
    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(@Param("user") User user);

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
     * 修改手机号码
     * @param user_id
     * @param newPhone
     * @return
     */
    int updatePhone(@Param("user_id") Integer user_id,@Param("newPhone") String newPhone);


    /**
     * 修改邮箱
     * @param user_id
     * @param newEmail
     * @return
     */
    int updateEmail(@Param("user_id") Integer user_id,@Param("newEmail") String newEmail);


    /**
     * 修改用户基本信息
     * @param user
     * @return
     */
    int updateData(@Param("user") User user);

    /**
     * 根据OpenId查询用户
     * @param openId
     * @return
     */
    User queryUserByOpenid(String openId);

    /**
     * 根据手机号或邮箱查询用户
     * @param user
     * @return
     */
    User queryUserByEmailOrPhone(@Param("user") User user);

    /**
     * 根据手机号或邮箱修改用户密码
     * @param email
     * @param pwd
     * @return
     */
    int updatePwdByEmail(@Param("email") String email,@Param("pwd") String pwd);

    int updatePwdByPhone(@Param("phone") String phone,@Param("pwd") String pwd);

    /**
     * 查询所有的用户fy
     * @return
     */
    List<User> getAlluser(@Param("page") Integer page,@Param("size") Integer size);

    /**
     * 查询所有的用户
     * @return
     */
    List<User> getAllusery();

    /**
     * 模糊查询所有的用户
     * @return
     */
    List<User> getAlluseryCheck(@Param("value") String value,@Param("type") String type);

    /**
     * 封禁&解封用户
     * @param userId
     * @return
     */
    int banORout(@Param("userId") Integer userId,@Param("typeId") Integer typeId);

    /**
     * @author likai
     * 用户购买（兑换）课程扣除相应的积分
     * @param userID
     * @param userIntegrsl
     * @return
     */
    int deducuIntegralWithUser(Integer userID,Integer userIntegrsl);

}
