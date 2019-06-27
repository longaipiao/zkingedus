package com.zking.zkingedu.common.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/url")
public class URLController {

    //跳转到登陆界面
    @RequestMapping("/loginpage")
    public String page1(){
        return "admin/login";
    }

    //登陆成功后跳转到后台的index界面
    @RequestMapping("/index")
    public String page2(){
        return "admin/index";
    }

    //权限管理页面界面
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

    //跳转角色管理
    @RequestMapping("/roleadd")
    public String page5(){
        return "admin/jdy/admin-roleadd";
    }









}
