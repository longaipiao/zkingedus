package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.SystemDao;
import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 课程体系接口   服务实现层
 *
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {
    @Resource
    private SystemDao systemDao;

    /**
     * 获取所有体系
     * @return
     */
    @Override
    public List<System> systems(Integer systemFid) {
        return systemDao.systems(systemFid);
    }

    /**
     * 根据体系Id查询体系
     * @param systemID
     * @return
     */
    @Override
    public System system(Integer systemID) {
        return systemDao.system(systemID);
    }

    /**
     * 获取所有体系（后台）
     * @param query
     * @return
     */
    @Override
    public List<System> sysList(Map query) {
        return systemDao.sysList(query);
    }
}
