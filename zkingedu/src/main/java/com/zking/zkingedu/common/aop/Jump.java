package com.zking.zkingedu.common.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Jump {
    @RequestMapping(value = "/jump")
    public String jump(HttpServletRequest request){
        request.setAttribute("addr","delete");
        System.out.println("jump");
        return "user/index";
    }
}
