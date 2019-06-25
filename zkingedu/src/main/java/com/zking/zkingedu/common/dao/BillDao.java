package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Bill;

import java.util.List;

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

    /**
     * 前台用户查看自己的账单
     * @param userID
     * @return
     */
    List<Bill> myBills(Integer userID);
}
