package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Bill;

import java.util.List;

/**
 * @author likai
 * 账单表
 */
public interface BillService {

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
