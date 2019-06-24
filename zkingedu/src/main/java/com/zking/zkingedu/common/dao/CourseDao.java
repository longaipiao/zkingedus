package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程接口
 */
@Mapper
public interface CourseDao {
    /**
     * 根据类别和课程体系查询课程
     * @param free
     * @param fid
     * @return
     */
//    List<Course> courses(Integer free,Integer fid);
}
