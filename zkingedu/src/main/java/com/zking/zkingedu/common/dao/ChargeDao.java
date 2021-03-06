package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Charge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  likai
 * 充值记录接口
 */
public interface ChargeDao {

    /**
     * 得到所有的充值记录
     * @return
     */
    List<Charge> getCharges(Charge charge);

    /**
     * 添加充值记录
     * @param charge
     * @return
     */
    int insertCharge(Charge charge);

    /**
     * 根据ID删除记录
     * @param chargeID
     * @return
     */
    int delChargeByID(String chargeID);

    /**
     * 用户个人中心查看自己的充值记录  状态为0：正常的
     * @param userID
     * @return
     */
    List<Charge> getChargesByUserID(Integer userID);


}
