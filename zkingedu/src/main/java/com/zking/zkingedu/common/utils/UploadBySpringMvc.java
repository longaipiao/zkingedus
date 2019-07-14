package com.zking.zkingedu.common.utils;


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
        //获取服务器路径
        String contextPath = request.getSession().getServletContext().getRealPath("/");

        //判断文件是否为空
        if(!file.isEmpty()){
            System.out.println(contextPath+"\\img");
            //文件名称
            String name = String.valueOf(new Date().getTime()+"_"+file.getOriginalFilename());
            File destFile = new File(contextPath+"\\img",name);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }


            try {
                //转存文件
                file.transferTo(destFile);
                //request.setAttribute("url",name);
                User user = (User)request.getSession().getAttribute("user");
                User u = new User();
                u.setUserID(user.getUserID());
                u.setUserImg("/img/"+name);
                //调用修改方法
                userService.updateData(u);
                user.setUserImg("/img/"+name);
                request.getSession().setAttribute("user",user);
                System.out.println(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "user/userinfo";
        }
        return "user/userinfo";
    }


    @RequestMapping(value = "UploadPage")
    @ResponseBody
    public String fileUploadPage2(MultipartFile file, HttpServletRequest request){
        //获取服务器路径
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        //判断文件是否为空
        if(!file.isEmpty()){

            //文件名称
            String name = String.valueOf(new Date().getTime()+"_"+file.getOriginalFilename());
            File destFile = new File(contextPath+"\\img",name);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            try {
                //转存文件
                file.transferTo(destFile);
            } catch (IOException e) {
                System.out.println("123");
                e.printStackTrace();
            }

            Gson gson = new Gson();
            Map<String,Object> map = new HashMap<>();
            map.put("name","/img/"+name);
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
