package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSON;
import com.zking.zkingedu.common.model.Answer;
import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.model.Title;
import com.zking.zkingedu.common.service.CategoryService;
import com.zking.zkingedu.common.service.TitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/title")
public class TitleController {
    @Autowired
    private TitleService titleService;
    @Autowired
    private CategoryService categoryService;


    /**
     * 按条件获取50题
     * @param title
     * @return
     */
    @RequestMapping("/gettitles")
    public String page13(Title title, Model model,HttpSession session){
        List<Title> gettitles = titleService.gettitles(title);//获取所有的题目加答案
        Category getcat = categoryService.getcat(title.getTitleCid());//按照ID获取类别表的对象
        model.addAttribute("getCategoryName",getcat.getCategoryName());//展示渲染
        model.addAttribute("titles",gettitles);//数据渲染
        session.setAttribute("titles",gettitles);//阅卷需要
        model.addAttribute("size",gettitles.size());//判空
        return "user/courses/grid";

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
        Set<Integer> weizuo = new HashSet<>();//实例化一个装未做答案题号的集合
        List<Integer> cuoti = new ArrayList<>();//实例化一个装错误答案题号的集合
        int count = titles.size()-maps.size();//未做题目
        for(int i=0;i<titles.size();++i){
            if(maps.get(titles.get(i).getTitleID()+"")!=null){//答案的json中存在
                for (Answer answer : titles.get(i).getAnswers()) {//遍历该题目的答案
                    if(answer.getAnswerState()==1){//从4个答案中找出真确的答案
                        if(maps.get(titles.get(i).getTitleID()+"").equals(answer.getAnswerAbcd())){//答案的json的值等于正确答案的话
                            score+=2;//分数加2
                        }
                        else{
                            cuoti.add(answer.getAnswerTid());//错误的加入集合
                        }
                    }
                }
            }
            else{
                weizuo.add(titles.get(i).getTitleID());
            }
        }
//        StringBuffer ct = new StringBuffer();
//        for (Integer integer : cuoti) {
//            ct.append(integer+", ");
//        }
//        System.err.println(weizuo.size());
        map.put("score",score);
        map.put("cuoti",cuoti);
        map.put("size",cuoti.size());
        map.put("weizuosize",weizuo.size());
        map.put("weizuo",weizuo);
        return map;
    }



}
