package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.CourseType;

import java.util.List;

/**
 * 课程类别接口
 */
public interface CourseTypeService {
    /**
     * 获取所有课程类别
     * @return
     */
    List<CourseType> courseTypes();
}
