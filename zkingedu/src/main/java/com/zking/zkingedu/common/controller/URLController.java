package com.zking.zkingedu.common.controller;


import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/url")
public class URLController {
    @Autowired
    private EmpService empService;

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
//    @RequestMapping("/loginpage")
//    public String page1(HttpSession session){
//        session.setAttribute("emp",null);
//        return "admin/login";
//    }


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

    //跳转到管理题库
    @RequestMapping("/title")
    public String page12(){
        return "admin/jdy/admin-title";
    }

    //跳转到课程管理
    @RequestMapping("/cours")
    public String cours(){
        return "admin/liuxuqing/admincou.html";
    }
}
