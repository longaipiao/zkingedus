package com.zking.zkingedu.common.aop;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;

@Order(2)
@Aspect
@Component
public class AopAddressIp {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletResponse response;
    /**
     * 定义切入点
     */
    @Pointcut("execution( * com.zking.zkingedu.common.controller.*.*(..))")
    public void test(){

    }

    @Before("test()")
    public boolean addrAop(){//检测用户ip地址是否对应
        boolean f = true;
        //System.out.println("切面检测");
        User u;
        if(session.getAttribute("user")!=null){
            u = (User)session.getAttribute("user");
            if(u.getUserIP()==null){
                return false;
            }
        }else{
            return false;
        }

        User user = userService.getUserByid(u.getUserID());
        if(!IpAddress.getIpAddr(request).equals(user.getUserIP())){//对比数据库中与访问的ip地址是否对应
            f = false;
            System.out.println(IpAddress.getIpAddr(request));
            //退出shiro
           //SecurityUtils.getSubject().logout();
        }
        if(f==false){
            try {
                response.sendRedirect("/jump");
                System.out.println(response);
                return false;
            } catch (IOException e) {
                System.out.println("重定向Io异常");
                e.printStackTrace();
            } catch (Exception e){
                System.out.println("重定向exception");
                e.printStackTrace();
            }
        }
        return true;
    }


}
