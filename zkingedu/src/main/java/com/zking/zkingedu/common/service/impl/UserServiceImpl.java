package com.zking.zkingedu.common.service.impl;


import com.zking.zkingedu.common.dao.UserDao;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;



    /**
     * @param userID
     * @param userIntegrsl
     * @return
     * @author likai
     * 用户充值增加积分
     */
    @Override
    public int addIntegral(Integer userID, String userIntegrsl) {
        return userDao.addIntegral(userID,userIntegrsl);
    }

    /**
     * @param userID
     * @param userIntegrsl
     * @return
     * @author likai
     * 用户购买（兑换）课程扣除相应的积分
     */
    @Override
    public int deducuIntegralWithUser(Integer userID, Integer userIntegrsl) {
        return userDao.deducuIntegralWithUser(userID,userIntegrsl);
    }
    @Override
    public User queryUserByOpenid(String openId) {
        return userDao.queryUserByOpenid(openId);
    }

    @Override
    public User queryUserByEmailOrPhone(User user) {
        return userDao.queryUserByEmailOrPhone(user);
    }

    @Override
    public int updatePwdByEmail(String email, String pwd) {
        return userDao.updatePwdByEmail(email,pwd);
    }

    @Override
    public int updatePwdByPhone(String phone, String pwd) {
        return userDao.updatePwdByPhone(phone, pwd);
    }

    @Override
    public List<User> getAlluser(Integer page, Integer size) {
        return userDao.getAlluser(page, size);
    }

    @Override
    public List<User> getAllusery() {
        return userDao.getAllusery();
    }

    @Override
    public List<User> getAlluseryCheck(String value, String type) {
        return userDao.getAlluseryCheck(value, type);
    }

    @Override
    public int banORout(Integer userId, Integer typeId) {
        return userDao.banORout(userId, typeId);
    }


    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public String queryPhone(String phone) {
        return userDao.queryPhone(phone);
    }



    @Override
    public User userLogin(User user) {
        return userDao.userLogin(user);
    }

    @Override
    public int updateAddressAndTime(String address, String lastTime,Integer uid) {
        return userDao.updateAddressAndTime(address,lastTime,uid);
    }

    @Override
    public User getUserByid(Integer uid) {
        return userDao.getUserByid(uid);
    }

    @Override
    public User getUserByName(String phone) {
        return userDao.getUserByName(phone);
    }

    @Override
    public int addQqLogin(User user) {
        return userDao.addQqLogin(user);
    }

    @Override
    public int updatePhone(Integer user_id, String newPhone) {
        return userDao.updatePhone(user_id,newPhone);
    }

    @Override
    public int updateEmail(Integer user_id, String newEmail) {
        return userDao.updateEmail(user_id,newEmail);
    }


    @Override
    public int updateData(User user) {
        return userDao.updateData(user);
    }
}
