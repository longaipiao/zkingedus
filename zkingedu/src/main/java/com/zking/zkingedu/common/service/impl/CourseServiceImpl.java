package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CourseDao;
import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 课程接口
 * 服务层
 * 实现
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    /**
     * 根据课程收费状态、课程类别查询
     * @param query
     * @return
     */
    @Override
    public List<Course> courses2(Map query) {
        return courseDao.courses2(query);
    }

    /**
     * 根据体系id获取体系下的课程数量
     * @param systemID
     * @return
     */
    @Override
    public Integer ccount(Integer systemID) {
        return courseDao.ccount(systemID);
    }

    /**
     * 根据体系ID获取体系下所有课程
     * @param systemID
     * @return
     */
    @Override
    public List<Course> courses(Integer systemID) {
        return courseDao.courses(systemID);
    }


    /**
     * 根据课程Id查询课程、课程类别、讲师
     * @param courseID
     * @return
     */
    @Override
    public Map course(Integer courseID) {
        return courseDao.course(courseID);
    }

    /**
     * 根据课程Id查询收藏此课程的人数
     * @param courseID
     * @return
     */
    @Override
    public Integer collection(Integer courseID) {
        return courseDao.collection(courseID);
    }

    /**
     * 根据讲师Id查询发布课程数
     * @param empID
     * @return
     */
    @Override
    public Integer announcedc(Integer empID) {
        return courseDao.announcedc(empID);
    }

    /**
     * 根据课程Id获取章节、视频
     * @param courseID
     * @return
     */
    @Override
    public List<Map> sections(Integer courseID) {
        return courseDao.sections(courseID);
    }

    /**
     * 根据类别和课程体系查询课程
     * @param free
     * @param fid
     * @return
     */
//    @Override
////    public List<Course> courses(Integer free, Integer fid) {
////        return courseDao.courses(free,fid);
////    }

    /**
     * @param courseID
     * @return
     * @author likai
     * 根据课程id得到所有的课程信息
     */
    @Override
    public Course getCourseByCourseID(Integer courseID) {
        return courseDao.getCourseByCourseID(courseID);
    }
}
