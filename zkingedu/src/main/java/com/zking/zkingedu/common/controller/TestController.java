package com.zking.zkingedu.common.controller;


import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import com.zking.zkingedu.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 无权限操作界面
 */

@Controller
@Slf4j

public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @RequestMapping("/")
    public String test(HttpServletRequest request){
        System.out.println("首页测试");
        return "user/index";
    }
    @RequestMapping(value = "/binding")
    public String qqLogin(){
        System.out.println("binding");
        return "user/binding";
    }

    @RequestMapping(value = "/admin1")
    public String testShiro1(){
        System.err.println("来了admin1");
        return "user/admin1";
    }

    @RequestMapping(value = "/test1")
    public String testShiro2(){
        System.err.println("来了test1");
        return "user/test1";
    }

    @Resource
    private EmpDao empDao;

    @ResponseBody
    @RequestMapping("/redis")
    public List<Emp> getEmps(){
        log.info("请求成功。。。");
        return empDao.getemps();
    }

}
