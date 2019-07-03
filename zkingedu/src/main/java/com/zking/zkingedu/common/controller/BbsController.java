package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.service.PostService;
import com.zking.zkingedu.common.service.SortService;
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
public class BbsController {
    @Autowired
    private PostService postService;
    @Autowired
    private SortService sortService;

    /**
     * 根据id查找帖子
     * @param post_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkBbs")
    public String checkBbs(Integer post_id, HttpServletRequest request){
        Post post = new Post();
        post.setPostID(post_id);
        List<Map<String, Object>> list = postService.queryPostByid(post);
        request.setAttribute("post",list.get(0));
        return "user/show2";
    }

    /**
     * 查看所有帖子
     * @param request
     * @return
     */
    @RequestMapping(value = "/post")
    public String checkType(HttpServletRequest request){
        request.setAttribute("sorts",sortService.queryAllSort());
        return "user/post";
    }

    /**
     * 发布帖子
     * @param post
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addBbs")
    public String addPost(Post post){
        post.setPostUid(41);
        post.setPostTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        post.setPostNum(0);
        return postService.addPost(post)>0?"1":"0";
    }


}
