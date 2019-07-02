package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Advertising;
import com.zking.zkingedu.common.service.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
@RequestMapping(value = "/advertising")
public class AdvertisingController {
    @Autowired
    private AdvertisingService advertisingService;


    @RequestMapping(value = "/add")
    @ResponseBody
    public String addAdvertising(Advertising advertising){
        advertising.setAdvertisingState(0);
        return advertisingService.addAdvertising(advertising)>0?"1":"2";
    }

    @RequestMapping(value = "/state")
    @ResponseBody
    public String updateAdvertisingState(Integer type,Integer aid){
        Long l = advertisingService.queryAdvertisingSum();
        if(type==1){
            if(l<=1){
                return "4";
            }
        }else {
            if(l>=5){
                return "3";
            }
        }

        return advertisingService.updateAdvertising(aid,type)>0?"1":"2";
    }
}
