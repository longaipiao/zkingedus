package com.zking.zkingedu.common.service.impl;


import com.zking.zkingedu.common.dao.UserDao;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
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
}
