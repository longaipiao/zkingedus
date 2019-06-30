package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.AdvertisingDao;
import com.zking.zkingedu.common.model.Advertising;
import com.zking.zkingedu.common.service.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告接口实现  实现
 */
@Service("advertisingService")
public class AdvertisingServiceImpl implements AdvertisingService {
    @Autowired
    private AdvertisingDao advertisingDao;

    @Override
    public List<Advertising> getAllAdvertising() {
        return advertisingDao.getAllAdvertising();
    }

    @Override
    public int addAdvertising(Advertising advertising) {
        return advertisingDao.addAdvertising(advertising);
    }

    @Override
    public int updateAdvertising(Integer aid, Integer type) {
        return advertisingDao.updateAdvertising(aid, type);
    }
}
