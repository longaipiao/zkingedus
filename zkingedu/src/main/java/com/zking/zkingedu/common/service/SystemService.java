package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.System;

import java.util.List;

/**
 * 课程体系接口
 */
public interface SystemService {
    /**
     * 获取所有体系
     * @return
     */
    List<System> systems();
}
