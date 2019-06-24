package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.User;

/**
 * 用户接口
 */
public interface UserDao {

    /**
     * @author likai
     *      用户充值增加积分
     * @param userID
     * @param userIntegrsl
     * @return
     */
    int addIntegral(Integer userID,String userIntegrsl);
}
