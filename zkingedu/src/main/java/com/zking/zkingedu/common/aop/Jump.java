package com.zking.zkingedu.common.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Jump {
    @RequestMapping(value = "/jump")
    public String jump(){
        System.out.println("jump");
        return "user/index";
    }
}
