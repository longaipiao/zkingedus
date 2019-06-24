package com.zking.zkingedu.common.aop;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Aspect
@Component
@Order(1)//设置AOP优先级，数字由小执行到大
public class AopUserRole {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    /**
     * 定义切入点
     */
    @Pointcut("execution( * com.zking.zkingedu.common.controller.UserRoleController.*(..))")
    public void test(){

    }

    @Before("test()")
    public boolean test1(){
        System.out.println("用户权限切面检测");
        if(request.getSession().getAttribute("user")==null){//检测用户是否登陆，未登录直接返回首页
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                System.out.println("重定向");
                e.printStackTrace();
            }
            return false;
        }else{//检测用户ip地址是否对应，不对应直接回首页
            //获取该用户的id
            User u = (User)request.getSession().getAttribute("user");
            //获取该用户
            User user = userService.getUserByid(u.getUserID());
            if(!user.getUserIP().equals(IpAddress.getIpAddr(request))){//对比访问ip和数据库ip是否对应
                try {
                    response.sendRedirect("/");
                } catch (IOException e) {
                    System.out.println("重定向");
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
