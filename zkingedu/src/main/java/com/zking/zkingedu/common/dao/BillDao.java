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
     * @param billID
     * @return
     */
    List<Bill> myBills(Integer billID);


    /**
     * 后台的账单记录统计   billShow.html
     * @param bill
     * @return
     */
    List<Bill> getAdminBills(Bill bill);


    /**
     * 后台账单记录的删除  billShow.html
     * @param billID
     * @return
     */
    int delBill(Integer billID);
}
