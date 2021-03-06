package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 充值记录表
 * @ClassName Charge
 * @Author likai
 **/
@Data
@Component
public class Charge implements Serializable {
    private static final long serialVersionUID = 8001550989483865390L;
    //'id
    private Integer chargeID;
    //用户id
    private Integer chargeUid;
    //金额
    private Integer chargeMoney;
    //积分
    private Integer chargeIntegral;
    //'充值时间
    private String chargeTime;

    //前台状态0：正常  1：隐藏
    private Integer chargeState;


    //用户对象
    private User user;
}
