package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.CourseTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 课程Controller
 */
@Controller
@Slf4j
public class CourseController {
    @Resource
    private CourseService courseService;
    @Resource
    private CourseTypeService courseTypeService;

    /**
     * 跳转到课程搜索
     */
    @RequestMapping("/courseindex/{Tid}")
    public String courseIndex(@PathVariable("Tid") Integer Tid,HttpServletRequest request){
        //获取课程类别
        List<CourseType> courseTypes = courseTypeService.courseTypes();
        request.setAttribute("courseTypes",courseTypes);
//        System.out.println(Tid);
//        System.out.println(courseTypes);
        request.setAttribute("Tid",Tid);
        request.setAttribute("courseTypes",courseTypes);
        return "user/courses/index.html";
    }

    /**
     * 跳转到课程详情页面
     * @param courseID
     * @param request
     * @return
     */
    @RequestMapping("/courseshow/{courseID}")
    public String courseShow(@PathVariable("courseID") Integer courseID, HttpServletRequest request){
        //根据课程Id查询课程、课程类别、讲师
        Map course = courseService.course(courseID);
        request.setAttribute("course",course);
        //获取此课程收藏数
        Integer collection = courseService.collection(courseID);
        request.setAttribute("collection",collection);
        //获取该讲师发布课程数
        Integer announcedc = courseService.announcedc(Integer.parseInt(course.get("emp_id").toString()));
        request.setAttribute("announcedc",announcedc);
        //根据课程Id获取章节、视频
        List<Map> sections = courseService.sections(courseID);
        request.setAttribute("sections",sections);
        System.out.println(course);
        System.out.println(sections);
        return "user/courses/show.html";
    }
}
