package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.CourseTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
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

    @RequestMapping(value = "courses2",method = RequestMethod.POST)
    @ResponseBody
    public Map courses2(@RequestBody Map query){
        //根据课程收费状态、课程类别查询
        List<Course> courses = courseService.courses2(query);

        //返回的Map
        Map map2=new LinkedHashMap();

        //判断课程集合是否为空
        if(courses.size()==0){
            map2.put("code",0);
        }else{
            System.out.println(courses);
            map2.put("courses",courses);
        }
        return map2;
    }

    /**
     * 跳转到课程搜索
     */
    @RequestMapping("/courseindex/{Tid}/{free}")
    public String courseIndex(@PathVariable("Tid") Integer Tid, @PathVariable("free") Integer free,HttpServletRequest request){
        //获取所有课程类别
        List<CourseType> courseTypes = courseTypeService.courseTypes();
        request.setAttribute("courseTypes",courseTypes);
        //将课程是否免费、课程分类参数存入request
        request.setAttribute("free",free);
        request.setAttribute("Tid",Tid);

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
//        System.out.println(course);
//        System.out.println(sections);
        return "user/courses/show.html";
    }
}
