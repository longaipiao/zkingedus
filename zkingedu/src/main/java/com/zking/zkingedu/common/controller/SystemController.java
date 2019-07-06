package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

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

        java.lang.System.out.println("获得的systemID："+systemID+"  体系："+systems);

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



    /**
     * 跳转到后台体系展示界面
     * @return
     */
    @RequestMapping("/admin/systems")
    public String adsystems(){
        java.lang.System.out.println("进后台体系界面");
        return "admin/liuxuqing/sysmanagement.html";
    }

    /**
     * 获取所有体系（后台）
     * @return
     */
    @RequestMapping("/admin/sysList")
    @ResponseBody
    public Map<String,Object> sysList(HttpServletRequest request){
        //查询体系的条件
        Map<String,Object> query=new HashMap<>();

        //获取页码及展示数
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        Integer page = (Integer.parseInt(request.getParameter("page"))-1)*limit;
        String sysName = request.getParameter("sysName");
        String fid = request.getParameter("fid");

        java.lang.System.out.println("page："+page+"  limit："+limit+"  sysName："+sysName+"  fid："+fid);

        query.put("page",page);
        query.put("limit",limit);
        query.put("sysName",sysName);
        query.put("fid",fid);

        //获取所有体系
        List<System> sysList = systemService.sysList(query);

        java.lang.System.out.println(sysList);

        //返回给前台的List
        List<Map> systems=new ArrayList<>();

        //返回到前台的数据
        Map<String,Object> map;
        for (System system : sysList) {
            map=new HashMap<>();
            map.put("systemID",system.getSystemID());
            map.put("systemName",system.getSystemName());
            map.put("systemFid",system.getSystemFid());
            //父体系名称
            if (system.getSystemFid()==0){
                map.put("systemFName","无");
            }else {
                //根据Id查询体系
                String systemFName = systemService.system(system.getSystemFid()).getSystemName();
                map.put("systemFName",systemFName);
            }
            map.put("systemDesc",system.getSystemDesc());
            map.put("systemImg",system.getSystemImg());
            systems.add(map);
        }
        java.lang.System.out.println(systems);

        //获取体系数量
        Integer syscount = systemService.syscount(query);

        //返回的集合
        Map map1=new HashMap();
        map1.put("code",0);
        map1.put("data",systems);
        map1.put("count",syscount);

        return map1;
    }

    /**
     * 体系图片的修改
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value="/admin/upSysimg")
    @ResponseBody
    public String uploadSource(@RequestParam("file") MultipartFile file , HttpServletRequest request) {
        java.lang.System.out.println("修改体系图片的方法");
        //存放图片路径
        String pathString = null;
        //获取当前时间
        String time=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //获取图片在此项目的路径
        String systemImg=null;
        if(file!=null) {
            pathString = "E:\\代码\\Y2\\SpringBoot\\zkingedus\\zkingedu\\target\\classes\\static\\user\\img\\system\\"+time+"_" +file.getOriginalFilename();
            systemImg="/user/img/system/"+time+"_" +file.getOriginalFilename();
        }
        try {
            File files=new File(pathString);
            //打印查看上传路径
            if(!files.getParentFile().exists()){
                files.getParentFile().mkdirs();
            }
            file.transferTo(files);

            java.lang.System.out.println("pathString:"+pathString+"  systemImg："+systemImg);

            //图片上传成功返回图片路径
            return "{\"code\": 0,\"data\": {\"src\": \""+systemImg+"\"}}";
        } catch (Exception e) {
            e.printStackTrace();
            //上传失败返回参数
            return "{\"code\": 1}";
        }
    }

    /**
     * 体系修改
     * @param system
     * @return
     */
    @RequestMapping(value = "/admin/systemUpd")
    @ResponseBody
    public Integer systemUpd(System system){
        java.lang.System.out.println(system);

        //根据体系Id修改体系
        Integer n = systemService.upSys(system);

        return n;
    }

    /**
     * 添加体系
     * @param system
     * @return
     */
    @RequestMapping(value = "/admin/systemAdd")
    @ResponseBody
    public Integer systemAdd(System system){
        java.lang.System.out.println(system);

        //添加体系
        Integer n = systemService.systemAdd(system);

        java.lang.System.out.println(n);
        return n;
    }

}
