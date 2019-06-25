package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Course;

import java.util.List;
import java.util.Map;

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
//    List<Course> courses(Integer free, Integer fid);

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
}
