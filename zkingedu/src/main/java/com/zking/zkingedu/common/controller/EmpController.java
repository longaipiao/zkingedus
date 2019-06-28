package com.zking.zkingedu.common.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
public class EmpController {
    @Autowired
    private  EmpService empService;

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
            //将认证通过的主体存入Session
            session.setAttribute("emp", emp);
            map.put("message", 0);
        } catch (Exception e) {
            map.put("message", 1);
            return map;
        }
        return map;
    }

    /**
     * 查询所有的员工（后台用户）
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getemps")
    @ResponseBody
    public Map getempall(@Param("page") String page,@Param("limit") String limit,String str){
        if(str==null){
            str = "";
        }
        Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        Map<String,Object> empmap = new HashMap<>();
        List<Emp> getemps = empService.getemps("%"+str+"%");
        empmap.put("code","0");
        empmap.put("msg","");
        empmap.put("count",objects.getTotal());
        empmap.put("data",getemps);
        return empmap;
    }

    /**
     * 修改后台用户状态
     * @param empid
     * @param state
     * @return
     */
    @RequestMapping("/state")
    @ResponseBody
    public String updateState(String empid,String state,Emp emp){
        if(empid==null||state==null){
            return "no";
        }
        emp.setEmpID(Integer.parseInt(empid));
        if("true".equals(state)){
            emp.setEmpState(0);
        }
        else{
            emp.setEmpState(1);
        }
        empService.updateempbyid(emp);
        return "ok";
    }

    /**
     * 删除员工
     * @param empid
     */
    @RequestMapping("/delemp")
    @ResponseBody
    public String deleteempbyid(String empid){
        if(empid!=null){
            empService.delempbyid(Integer.parseInt(empid));
        }
        return "ok";
    }

    /**
     * 直接修改员工密码
     * @param emp
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateemp")
    public String updateempbyid(Emp emp){
        if(emp!=null){
            empService.updateempbyid(emp);
        }
        return "ok";
    }
    /**
     * 修改员工角色
     */
    @RequestMapping("updateemproleid")
    @ResponseBody
    public String updateemproleid(String empid, String roleid){
        int updateemproleid = empService.updateemproleid(Integer.parseInt(empid), Integer.parseInt(roleid));
        if(updateemproleid<0){
            return "no";
        }
        return "ok";
    }

    /**
     * 添加员工表  添加员工角色表
     * @param jsons
     * @return
     */
    @RequestMapping("addemp")
    @ResponseBody
    public String addemp(String jsons, Emp emp, Role role){
        Map maps = (Map)JSON.parse(jsons);
        String empname = (String)maps.get("username");
        String emppwd = (String)maps.get("password");
        String roleid = (String)maps.get("interest");
        int rid = 0;
        if(roleid!=null){
            rid = Integer.parseInt(roleid);
        }
        //添加员工
        String nowDate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
        emp.setEmpName(empname);
        emp.setEmpPassword(emppwd);
        emp.setEmpIntegral("/user/img/1.jpg");
        emp.setEmpTime(nowDate);
        emp.setEmpState(0);
        emp.setEmpError(0);
        empService.addemp(emp);
        Integer empID = emp.getEmpID();
        empService.addt_emp_role(empID,rid);
        return "ok";
    }
}
