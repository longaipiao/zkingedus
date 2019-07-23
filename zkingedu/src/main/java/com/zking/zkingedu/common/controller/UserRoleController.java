package com.zking.zkingedu.common.controller;

import com.google.gson.Gson;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.PostService;
import com.zking.zkingedu.common.service.TcommentService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
@RequestMapping(value = "user")
@Transactional
public class UserRoleController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private TcommentService tcommentService;

    //跳转个人中心
    @RequestMapping("/userinfo")
    public String userInfo(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "user/index";
        }
       /* List<Map<String, Object>> maps = postService.queryPostByUid(user.getUserID());
        if(maps.size()!=0){
            for (Map<String, Object> map : maps) {
                //计算评论总数
                Integer post_id = Integer.parseInt(map.get("post_id1").toString());
                map.put("commentCount",tcommentService.queryTcomment(post_id).size());
            }
        }*/
        request.setAttribute("countPage",postService.queryPostByUid(user.getUserID()).size());
        return "user/userinfo";
    }

    @RequestMapping(value = "/pageMyPost")
    public String tt(Integer size,Integer page,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //计算start
        Integer start = (page-1)*size;
        List<Map<String, Object>> maps = postService.queryPostByUidPage(user.getUserID(),size,start);
        if(maps.size()!=0){
            for (int i=0;i<maps.size();i++){
                int post_state = Integer.parseInt(maps.get(i).get("post_state").toString());
                //计算评论总数
                Integer post_id = Integer.parseInt(maps.get(i).get("post_id1").toString());
                maps.get(i).put("commentCount",tcommentService.queryTcomment(post_id).size());
            }

        }
        model.addAttribute("upost",maps);
        model.addAttribute("countPage",postService.queryPostByUid(user.getUserID()).size());
        return "user/userinfo::pageTest";
    }

    /**
     * 修改手机号码
     * @param userId
     * @param phone
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updatePhone",method = RequestMethod.POST)
    public String updatePhone(Integer userId,String phone,HttpServletRequest request){
        int n = 0;
        try {
            n = userService.updatePhone(userId,phone);
        }catch (Exception e){
            System.out.println("修改手机号错误");
            e.printStackTrace();
        }
       User user = (User)request.getSession().getAttribute("user");
        user.setUserPhone(phone);
        request.getSession().setAttribute("user",user);
        return n+"";
    }

    /**
     * 修改邮箱地址
     * @param userId
     * @param email
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateEmail",method = RequestMethod.POST)
    public String updateEmail(Integer userId,String email,HttpServletRequest request){
        int n = 0;
        System.out.println(email);
        try {
            n = userService.updateEmail(userId,email);
        }catch (Exception e){
            System.out.println("修改邮件地址错误");
            e.printStackTrace();
        }
        User user = (User)request.getSession().getAttribute("user");
        user.setUserEmail(email);
        request.getSession().setAttribute("user",user);
        return n+"";
    }


    /**
     * 修改名字
     * @param userId
     * @param inputName
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateName",method = RequestMethod.POST)
    public String updateUname(Integer userId,String inputName,HttpServletRequest request){
        int n = 0;
        try {
            User user = new User();
            user.setUserID(userId);
            user.setUserName(inputName);
            n = userService.updateData(user);
        }catch (Exception e){
            System.out.println("修改名字错误1");
            e.printStackTrace();
        }
        User user = (User)request.getSession().getAttribute("user");
        user.setUserName(inputName);
        request.getSession().setAttribute("user",user);
        return n+"";
    }

    /**
     * 修改密码
     * @param userId
     * @param pwd
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updatePwd",method = RequestMethod.POST)
    public String updatePwd(Integer userId,String pwd,HttpServletRequest request){
        int n = 0;
        try {
            User user = new User();
            user.setUserID(userId);
            user.setUserPassword(pwd);
            n = userService.updateData(user);
        }catch (Exception e){
            System.out.println("修改密码错误");
            e.printStackTrace();
        }
       request.getSession().removeAttribute("user");
        return n+"";
    }

    @ResponseBody
    @RequestMapping(value = "/getPostCollection")
    public String getPostCollection(HttpServletRequest request,Integer page,Integer size){
        User user = (User) request.getSession().getAttribute("user");
        List<Map<String, Object>> list = postService.queryPostByUserId(user.getUserID());
        Gson gson = new Gson();
        Integer start = (page-1)*size;
        List<Map<String, Object>> pageList = postService.queryPostByUserIdPage(user.getUserID(),size,start);
        return gson.toJson(pageList);
    }

    @ResponseBody
    @RequestMapping(value = "/getPostCollectionCount")
    public String getPostCollectionCount(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<Map<String, Object>> list = postService.queryPostByUserId(user.getUserID());
        System.out.println("+++++++++++++++"+list.size());
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
