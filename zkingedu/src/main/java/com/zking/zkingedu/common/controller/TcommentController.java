package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Tcomment;
import com.zking.zkingedu.common.service.TcommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TcommentController {
    @Autowired
    private TcommentService tcommentService;

    /**
     * 评论帖子
     * @param tcomment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTcomment")
    public String addTcomment(Tcomment tcomment){
        tcomment.setTcommentTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        tcomment.setTcommentUid(41);
        tcomment.setTcommentFid(0);
        return tcommentService.addComment(tcomment)+"";
    }

    /**
     * 回复评论
     * @param tcomment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTcommentUser")
    public String addTcommentUser(Tcomment tcomment){
        tcomment.setTcommentTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        tcomment.setTcommentUid(41);
        return tcommentService.addComment(tcomment)+"";
    }
}
