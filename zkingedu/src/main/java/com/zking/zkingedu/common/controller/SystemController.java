package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 体系Controller
 */
@Controller
@Slf4j
public class SystemController {
    @Resource
    private SystemService systemService;
    @Resource
    private CourseService courseService;

    /**
     * 获取所有体系
     * @return
     */
    @RequestMapping(value = "/systems",method = RequestMethod.POST)
    @ResponseBody
    public Map systems(@RequestBody HashMap map2){
        java.lang.System.out.println("获取所有体系方法");
        //返回的map
        Map<String,Object> map=new HashMap<>();

        //接收传来的体系父ID
        int systemID = Integer.parseInt(map2.get("systemID").toString());

        //获取所有体系
        List<System> systems = systemService.systems(systemID);

        java.lang.System.out.println("获得的systemID："+systemID+"  体系"+systems);

        //获取体系下的课程
        List<Course> courses = courseService.courses(systemID);

        //遍历所有体系
        for (System system : systems) {
            //获取课程数
            Integer count = courseService.ccount(system.getSystemID());
            system.setCcount(count);
        }

        //将用户信息返回到前台
        if (systems.size()>0){
            map.put("code",1);
            map.put("data",systems);
            map.put("courses",courses);
        }
        return map;
    }

    @RequestMapping("/systemShow/{systemID}/{ccount}")
    public String systemShow(@PathVariable("systemID") Integer systemID,@PathVariable("ccount") Integer ccount,HttpServletRequest request){
        //根据传来的体系Id获取体系
        System system = systemService.system(systemID);
        system.setCcount(ccount);
        HttpSession session = request.getSession();
        //将体系放入request
        request.setAttribute("system",system);
        return "user/paths/show.html";
    }

}
