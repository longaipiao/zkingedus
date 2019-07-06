package com.zking.zkingedu.common.controller;

import com.google.gson.Gson;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.service.PostService;
import com.zking.zkingedu.common.service.SortService;
import com.zking.zkingedu.common.service.TcommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BbsController {
    @Autowired
    private PostService postService;
    @Autowired
    private SortService sortService;
    @Autowired
    private TcommentService tcommentService;

    /**
     * 根据id查找帖子并查看该帖子下的所有评论和回复
     * @param post_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkBbs")
    public String checkBbs(Integer post_id, HttpServletRequest request){
        Post post = new Post();
        post.setPostID(post_id);
        List<Map<String, Object>> list = postService.queryPostByid(post);//帖子
        List<Map<String, Object>> list1 = tcommentService.queryTcomment(post_id);//所有评论
        System.out.println();
        List<Map<String, Object>> tcomments = new ArrayList<>();//帖子评论集合
        List<Map<String, Object>> tcommentsUser = new ArrayList<>();//评论回复集合
        //筛选评论的回复
        for (Map<String, Object> map : list1) {
            int tcomment_fid = Integer.parseInt(map.get("tcomment_fid").toString());
            if(tcomment_fid==0){
                tcomments.add(map);
            }else{
                tcommentsUser.add(map);
            }
        }
        //将评论的回复放入帖子评论的下面
        for (Map<String, Object> tcomment : tcomments) {
            //定义评论下的回复集合
            List<Map<String, Object>> userTcomment = new ArrayList<>();
            int tcomment_id = Integer.parseInt(tcomment.get("tcomment_id").toString());
            for (Map<String, Object> map : tcommentsUser) {
                int tcomment_fid = Integer.parseInt(map.get("tcomment_fid").toString());

                //如果评论的回复等于此评论的ID
                if(tcomment_id==tcomment_fid){
                    map.put("HuserName","karabo");
                    userTcomment.add(map);
                    int tcomment_id2 = Integer.parseInt(map.get("tcomment_id").toString());
                    //再遍历一次查看回复中的回复
                    for (Map<String, Object> stringObjectMap : tcommentsUser) {
                        int tcomment_fid2 = Integer.parseInt(stringObjectMap.get("tcomment_fid").toString());
                        if(tcomment_id2==tcomment_fid2){
                            stringObjectMap.put("HuserName",map.get("user_name"));
                            userTcomment.add(stringObjectMap);
                        }
                    }
                }
            }
            //将回复的集合添加的评论的属性中
            tcomment.put("userTcomments",userTcomment);
        }

        //查询该用户是否收藏了该帖子
        Integer user_id = 41;
        Map<String, Object> map = new HashMap<>();//收藏点赞
        map.put("collection","");
        map.put("give","");
        if(tcommentService.queryCollection(post_id, user_id)!=null){
            map.put("collection","yes");
        }
        //查询该用户是否点赞了该帖子
        if(tcommentService.queryGiveById(post_id, user_id)!=null){
            map.put("give","yes");
        }

        //获取点赞数和收藏数
        Integer giveNum = tcommentService.queryCountGive(post_id);
        Integer postNum=tcommentService.queryCountPost(post_id);
        map.put("giveNum",giveNum);
        map.put("postNum",postNum);
        request.setAttribute("cORg",map);
        request.setAttribute("post",list.get(0));
        request.setAttribute("tcomments",tcomments);
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

    /**
     * 收藏or取消收藏
     * @param post_id
     * @param user_id
     * @param type_id
     * @param cTime
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addConllection")
    public String addConllection(Integer post_id, Integer user_id, Integer type_id, String cTime,Integer type){
        int n=0;
        String resultMsg="";
        if(type==1){//如果是收藏类型的操作
            cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            type_id = 1;
            user_id = 41;
            n = tcommentService.addCollection(post_id,user_id,type_id,cTime);
            if(n>0){
                resultMsg = "addCollection";
            }else{
                resultMsg = "addCError";
            }
        }else{
            user_id=41;
            n = tcommentService.deleteConllection(post_id,user_id);
            if(n>0){
                resultMsg = "delCollection";
            }else{
                resultMsg = "delCError";
            }
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping(value = "/addGive")
    public String addGive(Integer post_id,Integer type){
        Integer user_id = 41;
        String resultMsg="Error";
        if(type==1){
            if(tcommentService.addGive(post_id,user_id,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))>0){
                resultMsg = "addGive";
            }
        }else{
            if(tcommentService.delGive(post_id,user_id)>0){
                resultMsg = "delGive";
            }
        }
        return resultMsg;
    }


}
