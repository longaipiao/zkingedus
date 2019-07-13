package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Message;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.model.Tcomment;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.MessageService;
import com.zking.zkingedu.common.service.PostService;
import com.zking.zkingedu.common.service.TcommentService;
import com.zking.zkingedu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class TcommentController {
    @Autowired
    private TcommentService tcommentService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private Message message;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    /**
     * 评论帖子
     * @param tcomment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTcomment")
    public String addTcomment(Tcomment tcomment, HttpServletRequest request){
        tcomment.setTcommentTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        User user = (User) request.getSession().getAttribute("user");
        tcomment.setTcommentUid(user.getUserID());
        tcomment.setTcommentFid(0);
        //获取发帖人id
        Post post = new Post();
        post.setPostID(tcomment.getTcommentCid());
        List<Map<String, Object>> maps = postService.queryPostByid(post);
        Map<String, Object> map = maps.get(0);

        message.setMessageUid(Integer.parseInt(map.get("user_id").toString()));
        message.setMessageOtherUid(tcomment.getTcommentUid());
        message.setMessageName("评论消息");
        message.setMessageContent("用户："+userService.getUserByid(tcomment.getTcommentUid()).getUserName()+"评论了你的贴子");
        message.setMessagePid(tcomment.getTcommentCid());
        message.setMessageState(0);
        message.setMessageTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        messageService.insertMessage(message);

        return tcommentService.addComment(tcomment)+"";
    }

    /**
     * 回复评论
     * @param tcomment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTcommentUser")
    public String addTcommentUser(Tcomment tcomment,HttpServletRequest request){
        tcomment.setTcommentTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        User user = (User) request.getSession().getAttribute("user");
        tcomment.setTcommentUid(user.getUserID());

        //获取发帖人id
        Post post = new Post();
        post.setPostID(tcomment.getTcommentCid());
        List<Map<String, Object>> maps = postService.queryPostByid(post);
        Map<String, Object> map = maps.get(0);
        message.setMessageUid(Integer.parseInt(map.get("user_id").toString()));
        message.setMessageOtherUid(tcomment.getTcommentUid());
        message.setMessageName("回复评论消息");
        message.setMessageContent("用户："+userService.getUserByid(tcomment.getTcommentUid()).getUserName()+"回复了你的评论");
        message.setMessagePid(tcomment.getTcommentCid());
        message.setMessageState(0);
        message.setMessageTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        messageService.insertMessage(message);
        return tcommentService.addComment(tcomment)+"";
    }
}
