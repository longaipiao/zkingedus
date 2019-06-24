package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
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
        System.out.println("了user/user123");
        return "user/user123";
    }

}
