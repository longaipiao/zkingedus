package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSON;
import com.zking.zkingedu.common.model.Answer;
import com.zking.zkingedu.common.model.Title;
import com.zking.zkingedu.common.service.TitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/title")
public class TitleController {
    @Autowired
    private TitleService titleService;

//    /**
//     * 按条件获取50题
//     * @param title
//     * @return
//     */
    @RequestMapping("/gettitles")
    @ResponseBody
    public List gettitles(Title title){

        return titleService.gettitles(title);
    }
    /**
     * 按条件获取50题
     * @param title
     * @return
     */
    @RequestMapping("/text")
    public String page13(Title title, HttpSession session){
        List<Title> gettitles = titleService.gettitles(title);
        session.setAttribute("titles",gettitles);
        return "admin/html/grid";
    }

    /**
     * 阅卷
     * @param str
     * @return
     */
    @RequestMapping("/pingfen")
    @ResponseBody
    public Map pingfen(String str, HttpSession session){
        Map<String,Object> map = new HashMap();
        Map maps = (Map)JSON.parse(str);
        int score=0;
        List<Title> titles = (List<Title>)session.getAttribute("titles");
        List<Integer> cuoti = new ArrayList<>();//实例化一个装错误答案题号的集合
        int count = titles.size()-maps.size();//未做题目
        for(int i=0;i<titles.size();++i){
            if(maps.get(titles.get(i).getTitleID()+"")!=null){//答案的json中存在
                for (Answer answer : titles.get(i).getAnswers()) {//遍历该题目的答案
                    if(answer.getAnswerState()==0){//从4个答案中找出真确的答案
                        if(maps.get(titles.get(i).getTitleID()+"").equals(answer.getAnswerAbcd())){//答案的json的值等于正确答案的话
                            score+=2;//分数加2
                        }
                        else{
                            cuoti.add(answer.getAnswerTid());//错误的加入集合
                        }
                    }
                }
            }
        }
        StringBuffer ct = new StringBuffer();
        for (Integer integer : cuoti) {
            ct.append("第"+integer+"题  ");
        }
        map.put("score",score);
        map.put("cuoti",ct);
        map.put("weizuo",count);
        return map;
    }
}
