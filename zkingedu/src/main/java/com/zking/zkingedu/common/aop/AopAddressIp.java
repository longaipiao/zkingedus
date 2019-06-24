package com.zking.zkingedu.common.aop;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Aspect
@Component
public class AopAddressIp {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    /**
     * 定义切入点
     */
    @Pointcut("execution( * com.zking.zkingedu.common.controller.*.*(..))")
    public void test(){

    }

    @Before("test()")
    public boolean test1(){//检测用户ip地址是否对应
        System.out.println("切面检测");
        if(request.getSession().getAttribute("user")==null){
            return false;
        }
        User u = (User)request.getSession().getAttribute("user");
        if(u!=null){
            System.out.println(u);
            User user = userService.getUserByid(u.getUserID());
            if(!IpAddress.getIpAddr(request).equals(user.getUserIP())){//对比数据库中与访问的ip地址是否对应
                try {
                    response.sendRedirect("/");
                } catch (IOException e) {
                    System.out.println("重定向");
                    e.printStackTrace();
                }
                //退出shiro
               //SecurityUtils.getSubject().logout();
            }
        }
        return true;
    }
}
