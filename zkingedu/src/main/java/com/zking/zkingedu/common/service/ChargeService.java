package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Charge;

import java.util.List;

/**
 * @author  likai
 * 充值记录接口
 */
public interface ChargeService {

    /**
     * 得到所有的充值记录
     * @return
     */
    List<Charge> getCharges();

    /**
     * 添加充值记录
     * @param charge
     * @return
     */
    int insertCharge(Charge charge);
}
