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

}
