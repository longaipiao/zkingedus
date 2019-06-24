package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Course;

import java.util.List;

/**
 * 课程接口
 */
public interface CourseService {
    /**
     * 根据类别和课程体系查询课程
     * @param free
     * @param fid
     * @return
     */
    List<Course> courses(Integer free, Integer fid);
}
