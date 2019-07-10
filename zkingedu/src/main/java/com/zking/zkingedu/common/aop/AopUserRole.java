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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Aspect
@Component
@Order(1)//设置AOP优先级，数字由小执行到大
public class AopUserRole {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession session;
    /**
     * 定义切入点
     */
    @Pointcut("execution( * com.zking.zkingedu.common.controller.UserRoleController.*(..))")
    public void test(){

    }
    @Before("test()")
    public boolean roleAop(){
        System.out.println("权限切面检测");
        User u;
        if(session.getAttribute("user")!=null){
            u = (User)session.getAttribute("user");
        }else{
            try {
                System.out.println("重定向了");
                response.sendRedirect("/jump");
                System.out.println(response);
                return false;
            } catch (IOException e) {
                System.out.println("重定向Io异常");
                e.printStackTrace();
            } catch (Exception e){
                System.out.println("userRole重定向exception");
                e.printStackTrace();
            }
        }
        /*if(session.getAttribute("user")==null){//检测用户是否登陆，未登录直接返回首页
           f=false;
        }else{//检测用户ip地址是否对应，不对应直接回首页
            //获取该用户的id
            User u = (User)session.getAttribute("user");
            //获取该用户
            User user = userService.getUserByid(u.getUserID());
            if(!user.getUserIP().equals(IpAddress.getIpAddr(request))){//对比访问ip和数据库ip是否对应
                f=false;
            }
        }
        System.out.println(f);
        if(f==false){
            try {
                response.sendRedirect("/jump");
                return false;
            } catch (IOException e) {
                System.out.println("重定向Io异常");
                e.printStackTrace();
            } catch (Exception e){
                System.out.println("userRole重定向exception");
                e.printStackTrace();
            }
        }*/

        return true;
    }
}
