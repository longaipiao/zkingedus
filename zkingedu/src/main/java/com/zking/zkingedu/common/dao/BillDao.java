package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Bill;

/**
 * @author likai
 * 账单表
 */
public interface BillDao {

    /**
     * 增加账单
     * @param bill
     * @return
     */
    int insertBill(Bill bill);
}
