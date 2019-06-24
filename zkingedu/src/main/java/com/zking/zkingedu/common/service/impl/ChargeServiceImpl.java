package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.ChargeDao;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.service.ChargeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author likai
 * 充值记录接口服务层
 */
@Service("chargeService")
public class ChargeServiceImpl implements ChargeService {

    @Resource
    private ChargeDao chargeDao;

    /**
     * 得到所有的充值记录
     *
     * @return
     */
    @Override
    public List<Charge> getCharges( Charge charge) {
        return chargeDao.getCharges(charge);
    }

    /**
     * 添加充值记录
     *
     * @param charge
     * @return
     */
    @Override
    public int insertCharge(Charge charge) {
        return chargeDao.insertCharge(charge);
    }

    /**
     * 根据ID删除记录
     *
     * @param chargeID
     * @return
     */
    @Override
    public int delChargeByID(String chargeID) {
        return chargeDao.delChargeByID(chargeID);
    }
}
