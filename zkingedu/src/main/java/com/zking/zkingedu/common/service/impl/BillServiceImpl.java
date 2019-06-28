package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.BillDao;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账单表  实现
 */
@Service("billService")
public class BillServiceImpl implements BillService {

    @Resource
    private BillDao billDao;

    /**
     * 增加账单
     *
     * @param bill
     * @return
     */
    @Override
    public int insertBill(Bill bill) {
        return billDao.insertBill(bill);
    }

    /**
     * 前台用户查看自己的账单
     *
     * @param billID
     * @return
     */
    @Override
    public List<Bill> myBills(Integer billID) {

        return billDao.myBills(billID);
    }
}
