package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CourseTypeDao;
import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.service.CourseTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("courseTypeService")
public class CourseTypeServiceImpl implements CourseTypeService {
    @Resource
    private CourseTypeDao courseTypeDao;

    /**
     * 获取所有课程类别
     * @return
     */
    @Override
    public List<CourseType> courseTypes() {
        return courseTypeDao.courseTypes();
    }

    /**
     * 根据体系Id获取课程类别
     * @param systemID
     * @return
     */
    @Override
    public List<CourseType> cTypes(Integer systemID) {
        return courseTypeDao.cTypes(systemID);
    }
}
