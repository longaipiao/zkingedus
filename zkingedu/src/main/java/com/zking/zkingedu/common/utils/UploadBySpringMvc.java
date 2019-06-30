package com.zking.zkingedu.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadBySpringMvc{
    @Autowired
    private UserService userService;


    @RequestMapping(value = "fileUploadPage",produces = "application/text;charset=utf-8")
    public String fileUploadPage(MultipartFile file, HttpServletRequest request){

        //判断文件是否为空
        if(!file.isEmpty()){
            //文件存放路径
            String path = request.getServletContext().getRealPath("/images/");
            System.out.println(path);
            //文件名称
            String name = String.valueOf(new Date().getTime()+"_"+file.getOriginalFilename());
            File destFile = new File("F:\\git\\zkingedu2\\zkingedu\\target\\classes\\static\\user\\img",name);
            try {
                //转存文件
                file.transferTo(destFile);
                //request.setAttribute("url",name);
                User user = (User)request.getSession().getAttribute("user");
                User u = new User();
                u.setUserID(user.getUserID());
                u.setUserImg("/user/img/"+name);
                //调用修改方法
                userService.updateData(u);
                user.setUserImg("/user/img/"+name);
                request.getSession().setAttribute("user",user);
            } catch (IOException e) {
                System.out.println("");
                e.printStackTrace();
            }
            return "user/userinfo";
        }
        return "user/userinfo";
    }


    @RequestMapping(value = "UploadPage")
    @ResponseBody
    public String fileUploadPage2(MultipartFile file, HttpServletRequest request){
        System.out.println("来了");
        //判断文件是否为空
        if(!file.isEmpty()){
            //文件存放路径
            String path = request.getServletContext().getRealPath("/images/");
            //文件名称
            String name = String.valueOf(new Date().getTime()+"_"+file.getOriginalFilename());
            File destFile = new File("F:\\git\\zkingedu2\\zkingedu\\target\\classes\\static\\user\\img",name);
            System.out.println(name);
            try {
                //转存文件
                file.transferTo(destFile);
            } catch (IOException e) {
                System.out.println("123");
                e.printStackTrace();
            }

            Gson gson = new Gson();
            Map<String,Object> map = new HashMap<>();
            map.put("name","/user/img/"+name);
            String str = gson.toJson(map);
            return str;
        }
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("name","为空");
        String str = gson.toJson(map);
        return str;
    }
}
