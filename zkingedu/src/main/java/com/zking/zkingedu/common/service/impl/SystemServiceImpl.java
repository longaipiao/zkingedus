package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.SystemDao;
import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<System> systems() {
        return systemDao.systems();
    }
}
