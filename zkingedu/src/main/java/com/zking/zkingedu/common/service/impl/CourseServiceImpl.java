package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CourseDao;
import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * 根据类别和课程体系查询课程
     * @param free
     * @param fid
     * @return
     */
    @Override
    public List<Course> courses(Integer free, Integer fid) {
        return courseDao.courses(free,fid);
    }
}
