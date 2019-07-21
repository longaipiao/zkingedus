package com.zking.zkingedu.common.controller;


import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.AnswerService;
import com.zking.zkingedu.common.service.CategoryService;
import com.zking.zkingedu.common.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/url")
public class URLController {
    @Autowired
    private EmpService empService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AnswerService answerService;


    @RequestMapping(value = "/welcome")
    public String welcome(){
        return "admin/html/welcome";
    }
    /**
     *注销
     * @param request
     * @return
     */
    @RequestMapping("/loginpage")
    public String logOut(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        request.getSession().removeAttribute("emp");
        return "admin/login";
    }



//    //跳转到登陆界面
/*    @RequestMapping("/loginpage")
    public String page1(HttpSession session){
        session.setAttribute("emp",null);
        return "admin/login";
    }*/


    //树界面
    @RequestMapping("/admin-tree")
    public String page3(HttpServletRequest request){
        int roleID = Integer.parseInt(request.getParameter("roleID"));
        request.setAttribute("roleID",roleID);
        return "admin/jdy/admin-tree";
    }
//跳转角色管理
    @RequestMapping("/role")
    public String page4(){
        return "admin/jdy/admin-role";
    }

    //跳转角色增加
    @RequestMapping("/roleadd")
    public String page5(){
        return "admin/jdy/admin-roleadd";
    }

    //跳转后台主页
    @RequestMapping("/index")
    public String page6(){
        return "admin/jdy/admin-index";
    }

    //跳转角色管理
    @RequestMapping("/emp")
    public String page7(){
        return "admin/jdy/admin-emp";
    }

    //跳转修改角色
    @RequestMapping("/updaterole")
    public String page8(HttpServletRequest request){
        int empid = Integer.parseInt(request.getParameter("empid"));
        int roleid = empService.getroleid(empid);
        request.setAttribute("roleid",roleid);
        request.setAttribute("empid",empid);
        return "admin/jdy/updaterole";
    }
    //跳转到添加角色
    @RequestMapping("/addemp")
    public String page9(){
        return "admin/jdy/addemp";
    }
//    后台跳转到我的信息
    @RequestMapping("/mymessage")
    public String page10(){
        return "admin/jdy/mymessage";
    }

    //跳转到管理题库类别
    @RequestMapping("/category")
    public String page11(){
        return "admin/jdy/admin-category";
    }



    //展示题库类别数据(选择考试内容)*跳转到下拉框页面的
    @RequestMapping("/getcategorys")
    public String getcategorys(HttpSession session){
        List<Category> category = categoryService.getCategory();
        session.setAttribute("category",category);
        return "admin/jdy/fzselect";
    }

    //跳转到课程管理
    @RequestMapping("/cours")
    public String cours(){
        return "admin/liuxuqing/admincou.html";
    }

    /**
     * 跳转到章节添加页面
     * @param request
     * @return
     */
    @RequestMapping("/secAdd")
    public String secAdd(HttpServletRequest request){
        //接收传来的课程Id
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        request.setAttribute("courseId",courseId);

        return "/admin/liuxuqing/sectionAdd.html";
    }

    /**
     * 跳转到章节管理页面
     * @param request
     * @return
     */
    @RequestMapping("/secList")
    public String secList(HttpServletRequest request){
        //接收传来的课程Id
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        request.setAttribute("courseId",courseId);

        return "/admin/liuxuqing/sectioncurd.html";
    }

    @RequestMapping("/video")
    public String video(HttpServletRequest request){
        //接收传来的视频Id（视频播放地址）
        String videoUrl = request.getParameter("videoUrl");
//        System.out.println(videoUrl);
        request.setAttribute("videoUrl",videoUrl);

        return "/admin/liuxuqing/video.html";
    }


















    //------------------------------------前台-------------------------------------
    //去题库页面
    @RequestMapping("/tiku")
    public String pagetiku(Model model,Integer categoryFID){
        List<Category> category = categoryService.getCategory();//获取所有的题库类别
        List<Category> gettikuzitype = categoryService.gettikuzitype(categoryFID);
        model.addAttribute("gettikuzitype",gettikuzitype);//子
        model.addAttribute("category",category);//父
        return "user/courses/tiku";
    }

}
