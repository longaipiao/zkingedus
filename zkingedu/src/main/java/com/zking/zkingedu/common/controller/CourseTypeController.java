package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.service.CourseTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 课程类别Controller
 */
@Controller
@Slf4j
public class CourseTypeController {
    @Resource
    private CourseTypeService courseTypeService;

    /**
     * 根据体系Id获取课程类别
     * @return
     */
    @RequestMapping("/admin/cTypes")
    @ResponseBody
    public List<CourseType> allsys(HttpServletRequest request){
        //接收前台传来的体系Id
        Integer systemID = Integer.parseInt(request.getParameter("systemID"));
        //获取课程类别
        List<CourseType> courseTypes = courseTypeService.cTypes(systemID);

        java.lang.System.out.println(courseTypes);
        return courseTypes;
    }

    /**
     * 课程图片上传
     * @param file
     * @return
     */
    @RequestMapping(value="/admin/adcourseImg")
    @ResponseBody
    public String uploadSource(@RequestParam("file") MultipartFile file) {
        java.lang.System.out.println("增加课程图片的方法");
        //存放图片路径
        String pathString = null;
        //获取当前时间
        String time=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //获取图片在此项目的路径
        String courseImg=null;
        if(file!=null) {
            pathString = "E:\\代码\\Y2\\SpringBoot\\zkingedus\\zkingedu\\target\\classes\\static\\user\\img\\course\\"+time+"_" +file.getOriginalFilename();
            courseImg="/user/img/course/"+time+"_" +file.getOriginalFilename();
        }
        try {
            File files=new File(pathString);
            //打印查看上传路径
            if(!files.getParentFile().exists()){
                files.getParentFile().mkdirs();
            }
            file.transferTo(files);

            java.lang.System.out.println("pathString:"+pathString+"  courseImg："+courseImg);

            //图片上传成功返回图片路径
            return "{\"code\": 0,\"data\": {\"src\": \""+courseImg+"\"}}";
        } catch (Exception e) {
            e.printStackTrace();
            //上传失败返回参数
            return "{\"code\": 1}";
        }
    }
}
