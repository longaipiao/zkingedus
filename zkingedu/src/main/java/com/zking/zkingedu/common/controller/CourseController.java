package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.*;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.CourseTypeService;
import com.zking.zkingedu.common.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.System;
import java.util.HashMap;
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
    @Resource
    private SectionService sectionService;

    /**
     * 根据课程收费状态、课程类别、课程名称查询
     * @param query
     * @return
     */
    @RequestMapping(value = "courses2",method = RequestMethod.POST)
    @ResponseBody
    public Map courses2(@RequestBody Map query){
        //根据课程收费状态、课程类别、课程名称查询
        List<Course> courses = courseService.courses2(query);

        //返回的Map
        Map map2=new LinkedHashMap();

        //判断课程集合是否为空
        if(courses.size()==0){
            map2.put("code",0);
        }else{
//            System.out.println(courses);
            map2.put("courses",courses);
        }
        return map2;
    }

    /**
     * 跳转到课程搜索
     * @param Tid
     * @param free
     * @param courseName
     * @param request
     * @return
     */
    @RequestMapping("/courseindex/{Tid}/{free}/{courseName}")
    public String courseIndex(@PathVariable("Tid") Integer Tid, @PathVariable("free") Integer free, @PathVariable("courseName") String courseName, HttpServletRequest request){
        //获取所有课程类别
        List<CourseType> courseTypes = courseTypeService.courseTypes();
        request.setAttribute("courseTypes",courseTypes);
        //将课程是否免费、课程分类，课程搜索名字参数存入request
        request.setAttribute("free",free);
        request.setAttribute("Tid",Tid);
        request.setAttribute("courseName",courseName);
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
        //根据课程Id获取章节
        List<Section> sections = sectionService.sections(courseID);
        request.setAttribute("sections",sections);
//        System.out.println(course);
//        System.out.println(sections);
        //获取用户
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //判断用户不为空
        if (null!=user){
            //判断该用户是否收藏了课程
            Integer collectionID = courseService.Bookmarked(courseID, user.getUserID(), 0);
//        System.out.println(collectionID);
            request.setAttribute("collectionID",collectionID);
        }
        return "user/courses/show.html";
    }

    /**
     * 获取所有课程（后台）
     * @return
     */
    @RequestMapping("/admin/couList")
    @ResponseBody
    public Map<String,Object> sysList(HttpServletRequest request){
        //查询体系的条件
        Map<String,Object> query=new HashMap<>();

        //获取页码及展示数
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        Integer page = (Integer.parseInt(request.getParameter("page"))-1)*limit;
        String nametype = request.getParameter("nametype");
        String name = request.getParameter("name");
        //获取后台登录员工的Id
        HttpSession session = request.getSession();
        Emp emp = (Emp) session.getAttribute("emp");

        query.put("page",page);
        query.put("limit",limit);
        query.put("nametype",nametype);
        query.put("name",name);
        query.put("empID",emp.getEmpID());

        //获取所有课程及数量
        List<Map> maps = courseService.couList(query);
        Integer cuncour = courseService.cuncour(query);

//        System.out.println("page："+page+"  limit："+limit+"  nametype："+nametype+"  name:"+name+"  count："+cuncour);
//        System.out.println(maps);

        //返回的集合
        Map map1=new HashMap();
        map1.put("code",0);
        map1.put("data",maps);
        map1.put("count",cuncour);

        return map1;
    }

    /**
     * 增加课程
     * @param course
     * @return
     */
    @RequestMapping("/admin/courseAdd")
    @ResponseBody
    public Integer courseAdd(Course course,HttpServletRequest request){
        //获取后台登录员工的Id
        HttpSession session = request.getSession();
        Emp emp = (Emp) session.getAttribute("emp");
        //获得讲师Id
        course.setCourseEid(emp.getEmpID());

        //判断课程是否免费，若免费将课程积分设置为0
        if (course.getCourseFree()==0){
            course.setCourseInte(0);
        }

        //添加课程
        Integer n = courseService.couAdd(course);
        //获取前台传来的课程信息
//        System.out.println(course);

        return n;
    }

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    @RequestMapping("/admin/courseUpd")
    @ResponseBody
    public Integer courseUpd(Course course){
        //判断课程是否免费，若免费将课程积分设置为0
        if (course.getCourseFree()==0){
            course.setCourseInte(0);
        }

        //修改课程
        Integer n = courseService.couUpd(course);

        //获取前台传来的课程信息
//        System.out.println(course);
        return n;
    }

    /**
     * 删除课程
     * @param request
     * @return
     */
    @RequestMapping("/admin/couDel")
    @ResponseBody
    public Integer couDel(HttpServletRequest request){
        //接收前台传来的课程ID集合
        String courseIDs = request.getParameter("courseIDs");
        //分割成数组
        String[] couList = courseIDs.split(",");
        Integer n=0;
        try {
            //循环数组
            for (String s : couList) {
                //根据ID删除课程及底下章节
                n += courseService.couDel(Integer.parseInt(s));
                sectionService.secdelbyCid(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

        return n;
    }

    /**
     * 获取热门课程
     * @return
     */
    @RequestMapping("/hotCou")
    @ResponseBody
    public List<Course> hotCou(){
        List<Course> courses = courseService.hotCou();
//        System.out.println(courses);
        return courses;
    }

    /**
     * 获取某个体系热门课程
     * @param request
     * @return
     */
    @RequestMapping("/hotcoubySid")
    @ResponseBody
    public List<Course> hotcoubySid(HttpServletRequest request){
        //接收前台传来的体系ID
        Integer systemID = Integer.parseInt(request.getParameter("systemID"));

        //根据体系ID获取热门课程
        List<Course> courses = courseService.hotcoubySid(systemID);
        return courses;
    }

    /**
     * 修改学习人数
     * @param request
     * @return
     */
    @RequestMapping("/updNum")
    @ResponseBody
    public Integer updNum(HttpServletRequest request){
        //接收传来的课程ID
        Integer courseID = Integer.parseInt(request.getParameter("courseID"));
        Integer n = courseService.updNum(courseID);
        return n;
    }

    /**
     * 添加收藏
     * @param request
     * @return
     */
    @RequestMapping("/collAdd")
    @ResponseBody
    public Integer collAdd(HttpServletRequest request){
        //接收传来的课程ID
        Integer courseID = Integer.parseInt(request.getParameter("courseID"));
        //获取用户ID
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //添加收藏
        Integer n = courseService.collAdd(courseID, user.getUserID());
        return n;
    }

    /**
     * 取消课程收藏
     * @param request
     * @return
     */
    @RequestMapping("/collDel")
    @ResponseBody
    public Integer collDel(HttpServletRequest request){
        //接收前台传来的课程ID
        Integer courseID = Integer.parseInt(request.getParameter("courseID"));
        //获取用户ID
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //取消课程收藏
        Integer n = courseService.collDel(courseID, user.getUserID());
        return n;
    }

    /**
     * 相似课程
     * @param request
     * @return
     */
    @RequestMapping("/similarCou")
    @ResponseBody
    public List<Course> similarCou(HttpServletRequest request){
        //接收前台传来的课程ID、课程类别名
        Integer courseID = Integer.parseInt(request.getParameter("courseID"));
        Integer tid = Integer.parseInt(request.getParameter("tid"));
        List<Course> courses = courseService.similarCou(tid, courseID);
        return courses;
    }

    /**
     * 获取用户收藏课程
     * @return
     */
    @RequestMapping("/coucolls")
    @ResponseBody
    public List<Course> coucolls(HttpServletRequest request){
        HttpSession session = request.getSession();
        //获取用户ID
        User user = (User) session.getAttribute("user");
        //获取收藏课程
        List<Course> coucolls = courseService.coucolls(user.getUserID());
        System.out.println(coucolls);

        return coucolls;
    }
}
