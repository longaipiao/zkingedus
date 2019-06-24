package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.System;
import com.zking.zkingedu.common.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 体系Controller
 */
@Controller
@Slf4j
public class SystemController {
    @Resource
    private SystemService systemService;

    /**
     * 获取所有体系
     * @return
     */
    @RequestMapping(value = "/systems",method = RequestMethod.POST)
    @ResponseBody
    public Map systems(){
        //返回的map
        Map<String,Object> map=new HashMap<>();

        //获取所有体系
        List<System> systems = systemService.systems();

        //将用户信息返回到前台
        if (systems.size()>0){
            map.put("code",1);
            map.put("data",systems);
        }
        return map;
    }

}
