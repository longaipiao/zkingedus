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

    /**
     * 展示课程信息（后台）
     * @param map
     * @return
     */
    @Override
    public List<Map> couList(Map map) {
        return courseDao.couList(map);
    }

    /**
     * 计算课程数量
     * @param map
     * @return
     */
    @Override
    public Integer cuncour(Map map) {
        return courseDao.cuncour(map);
    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    @Override
    public Integer couAdd(Course course) {
        return courseDao.couAdd(course);
    }

    /**
     * 修改课程
     * @param course
     * @return
     */
    @Override
    public Integer couUpd(Course course) {
        return courseDao.couUpd(course);
    }

    @Override
    public Integer couDel(Integer courseID) {
        return courseDao.couDel(courseID);
    }

    /**
     * 获取学习人数最多的前五条课程
     * @return
     */
    @Override
    public List<Course> hotCou() {
        return courseDao.hotCou();
    }

    /**
     * 根据父体系ID获取热门课程前五条
     * @param systemID
     * @return
     */
    @Override
    public List<Course> hotcoubySid(Integer systemID) {
        return courseDao.hotcoubySid(systemID);
    }

    /**
     * 根据课程ID修改学习人数
     * @param courseID
     * @return
     */
    @Override
    public Integer updNum(Integer courseID) {
        return courseDao.updNum(courseID);
    }

    /**
     * 根据收藏（课程ID、帖子ID）ID，用户ID，收藏类型查询
     * @param courseID
     * @param userID
     * @param collectionState
     * @return
     */
    @Override
    public Integer Bookmarked(Integer courseID, Integer userID, Integer collectionState) {
        return courseDao.Bookmarked(courseID,userID,collectionState);
    }

    /**
     * 添加课程收藏
     * @param courseID
     * @param userID
     * @return
     */
    @Override
    public Integer collAdd(Integer courseID,Integer userID) {
        return courseDao.collAdd(courseID,userID);
    }

    /**
     * 取消课程收藏
     * @param courseID
     * @param userID
     * @return
     */
    @Override
    public Integer collDel(Integer courseID, Integer userID) {
        return courseDao.collDel(courseID,userID);
    }

    /**
     * 获取相似课程
     * @param tid
     * @param courseID
     * @return
     */
    @Override
    public List<Course> similarCou(Integer tid, Integer courseID) {
        return courseDao.similarCou(tid,courseID);
    }

    /**
     * 根据用户ID查询收藏课程
     * @param userID
     * @return
     */
    @Override
    public List<Course> coucolls(Integer userID) {
        return courseDao.coucolls(userID);
    }

}
