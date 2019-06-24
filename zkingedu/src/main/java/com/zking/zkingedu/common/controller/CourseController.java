package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程Controller
 */
@Controller
@Slf4j
public class CourseController {
    @Resource
    private CourseService courseService;

    /**
     * 跳转到课程首页
     */
    @RequestMapping("/courseindex")
    public String courseIndex(){
//        List<Course> courses = courseService.courses(0, 1);
//        System.out.println(courses);

        return "user/courses/index.html";
    }
}
