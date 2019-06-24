package com.zking.zkingedu.common.controller;


import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.service.EmpService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
public class EmpController {
    @Autowired
    private EmpService empService;
    @Autowired
    private RedisUtil redisUtil;
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


    /**
     * shiro登陆
     * @param emp
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public Map Login(Emp emp, HttpSession session){
        Map map = new HashMap();
        if(emp.getEmpName()==null){
            map.put("message", 2);
            return map;
        }
        //获取主体（当前状态为没有认证的状态“未认证”）
        Subject subject = SecurityUtils.getSubject();
        //登录后存放进shiro token
        UsernamePasswordToken token=new UsernamePasswordToken(emp.getEmpName(), emp.getEmpPassword());

        //登录方法（认证是否通过）
        //使用subject调用securityManager,安全管理器调用Realm
        try {
            //利用异常操作
            //需要开始调用到Realm中
            System.out.println("========================================");
            System.out.println("1、进入认证方法");
            subject.login(token);
            //获得Principal对象（Principal是一个包含用户的标识和用户的所属角色的对象）
            emp = (Emp) subject.getPrincipal();
            System.out.println(emp);
            //将认证通过的主体存入Session
            session.setAttribute("emp", emp);
            map.put("message", 0);
        } catch (Exception e) {
            map.put("message", 1);
            return map;
        }
        return map;
    }

    //个人权限菜单展示
    @ResponseBody
    @RequestMapping(value = "/getmuens")
    public List<Menu> getmuen(Integer empid){
        List<Menu> menus = empService.getmenus(empid);
        return menus;
    }




    /**
     * 测试
     * @param string
     * @return
     */
    @ResponseBody
    @RequestMapping("text")
    public Emp text(String string){
        return empService.getempbyempname(string);
    }



}
