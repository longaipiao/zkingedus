package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@Slf4j
@RequestMapping(value = "user")
@Transactional
public class UserRoleController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user123")

    public String test(){
        System.out.println("来了user/user123");
        return "user/user123";
    }

    @ResponseBody
    @RequestMapping(value = "updatePhone",method = RequestMethod.POST)
    public String updatePhone(Integer userId,String phone,HttpServletRequest request){
        int n = 0;
        try {
            n = userService.updatePhone(userId,phone);
        }catch (Exception e){
            System.out.println("修改手机号错误");
            e.printStackTrace();
        }
       User user = (User)request.getSession().getAttribute("user");
        user.setUserPhone(phone);
        request.getSession().setAttribute("user",user);
        return n+"";
    }

    @ResponseBody
    @RequestMapping(value = "updateEmail",method = RequestMethod.POST)
    public String updateEmail(Integer userId,String email,HttpServletRequest request){
        int n = 0;
        System.out.println(email);
        try {
            n = userService.updateEmail(userId,email);
        }catch (Exception e){
            System.out.println("修改邮件地址错误");
            e.printStackTrace();
        }
        User user = (User)request.getSession().getAttribute("user");
        user.setUserEmail(email);
        request.getSession().setAttribute("user",user);
        return n+"";
    }

    @ResponseBody
    @RequestMapping(value = "updateName",method = RequestMethod.POST)
    public String updateUname(Integer userId,String inputName,HttpServletRequest request){
        int n = 0;
        try {
            User user = new User();
            user.setUserID(userId);
            user.setUserName("inputName");
            n = userService.updateData(user);
        }catch (Exception e){
            System.out.println("修改名字错误");
            e.printStackTrace();
        }
        User user = (User)request.getSession().getAttribute("user");
        user.setUserName(inputName);
        request.getSession().setAttribute("user",user);
        return n+"";
    }

    @ResponseBody
    @RequestMapping(value = "updatePwd",method = RequestMethod.POST)
    public String updatePwd(Integer userId,String pwd,HttpServletRequest request){
        int n = 0;
        try {
            User user = new User();
            user.setUserID(userId);
            user.setUserPassword(pwd);
            n = userService.updateData(user);
        }catch (Exception e){
            System.out.println("修改密码错误");
            e.printStackTrace();
        }
       request.getSession().removeAttribute("user");
        return n+"";
    }

}
