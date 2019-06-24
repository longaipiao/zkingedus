package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.User;

/**
 * 用户 服务层
 */
public interface UserService {


    /**
     * @author likai
     *      用户充值增加积分
     * @param userID
     * @param userIntegrsl
     * @return
     */
    int addIntegral(Integer userID,String userIntegrsl);

}
