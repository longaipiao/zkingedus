package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 课程接口
 */
@Mapper
public interface CourseDao {
    /**
     * 根据课程收费状态、课程类别查询
     * @param query
     * @return
     */
    List<Course> courses2(Map query);

    /**
     * 根据体系id获取体系下的课程数量
     * @param systemID
     * @return
     */
    Integer ccount(Integer systemID);

    /**
     * 根据体系ID获取体系下所有课程
     * @param systemID
     * @return
     */
    List<Course> courses(Integer systemID);

    /**
     * 根据课程Id查询课程、课程类别、讲师
     * @param courseID
     * @return
     */
    Map course(Integer courseID);

    /**
     * 根据课程Id查询收藏此课程的人数
     * @param courseID
     * @return
     */
    Integer collection(Integer courseID);

    /**
     * 根据讲师Id查询发布课程数
     * @param empID
     * @return
     */
    Integer announcedc(Integer empID);

    /**
     * 根据课程Id获取章节、视频
     * @param courseID
     * @return
     */
    List<Map> sections(Integer courseID);

    /**
     * @author likai
     * 根据课程id得到所有的课程信息
     * @param courseID
     * @return
     */
    Course getCourseByCourseID(Integer courseID);

    /**
     * 展示所有课程信息（后台）
     * @param map
     * @return
     */
    List<Map> couList(Map map);

    /**
     * 计算课程数量
     * @param map
     * @return
     */
    Integer cuncour(Map map);

    /**
     * 添加课程
     * @param course
     * @return
     */
    Integer couAdd(Course course);

}
