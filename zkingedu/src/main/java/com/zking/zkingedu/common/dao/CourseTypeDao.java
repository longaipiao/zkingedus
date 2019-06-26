package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.CourseType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程类别接口
 */
@Mapper
public interface CourseTypeDao {
    /**
     * 获取所有课程类别
     * @return
     */
    List<CourseType> courseTypes();
}
