package com.zking.zkingedu.common.controller;


import com.google.gson.Gson;
import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Advertising;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.AdvertisingService;
import com.zking.zkingedu.common.service.PostService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 无权限操作界面
 */

@Controller
@Slf4j

public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdvertisingService advertisingService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PostService postService;
    @RequestMapping("/")
    public String test(HttpServletRequest request){
//        System.out.println("首页测试");
        return "user/index";
    }

    /**
     * 查看用户列表
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userAll")
    public String dataTest(Integer page,Integer limit){
        List<Map<String,Object>> l = new ArrayList<>();
        for (User user : userService.getAllusery()) {
            Map m = new HashMap();
            m.put("userID",user.getUserID());
            m.put("userName",user.getUserName());
            if(user.getUserEmail()==null||user.getUserEmail().length()==0){
                m.put("userEmail","未绑定邮箱");
            }else{
                m.put("userEmail",user.getUserEmail());
            }
            m.put("userPhone",user.getUserPhone());
            m.put("userImg","<img src='"+user.getUserImg()+"'>");
            m.put("userIntegrsl",user.getUserIntegrsl());
            if(user.getUserState()==0){
                m.put("userState","正常");
            }else{
                m.put("userState","封禁");
            }
            m.put("userIP",user.getUserIP());
            m.put("userLastTime",user.getUserLastTime());
            m.put("userRegTime",user.getUserRegTime());
            if(user.getUserOpenID()!=null&&user.getUserOpenID().length()!=0){
                m.put("userOpenID","QQ用户");
            }else{
                m.put("userOpenID","平台用户");
            }
            l.add(m);
        }
        Gson gson = new Gson();
        return gson.toJson(l);
    }
    /**
     * 展示后台用户数据
     * @param value
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userAllCheck")
    public String dataTestCheck(String value,String type){
        if(value==null||value.length()==0){
            value="";
        }
        List<Map<String,Object>> l = new ArrayList<>();
        for (User user : userService.getAlluseryCheck(value,type)) {
            Map m = new HashMap();
            m.put("userID",user.getUserID());
            m.put("userName",user.getUserName());
            if(user.getUserEmail()==null||user.getUserEmail().length()==0){
                m.put("userEmail","未绑定邮箱");
            }else{
                m.put("userEmail",user.getUserEmail());
            }
            m.put("userPhone",user.getUserPhone());
            m.put("userImg","<img src='"+user.getUserImg()+"'>");
            m.put("userIntegrsl",user.getUserIntegrsl());
            if(user.getUserState()==0){
                m.put("userState","正常");
            }else{
                m.put("userState","封禁");
            }
            m.put("userIP",user.getUserIP());
            m.put("userLastTime",user.getUserLastTime());
            m.put("userRegTime",user.getUserRegTime());
            if(user.getUserOpenID()!=null&&user.getUserOpenID().length()!=0){
                m.put("userOpenID","QQ用户");
            }else{
                m.put("userOpenID","平台用户");
            }
            l.add(m);
        }
        Gson gson = new Gson();
        return gson.toJson(l);
    }


    /**
     * 广告入口
     * @return
     */
    @RequestMapping(value = "/advertising")
    public String checkadvertisingData(){
        return "user/advertising";
    }

    /**
     * 广告管理
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/advertisingData")
    public String getAllAdvertising(){
        List<Map<String,Object>> list = new ArrayList<>();
        for (Advertising advertising : advertisingService.getAllAdvertising()) {
            Map<String,Object> map = new HashMap<>();
            map.put("advertisingID",advertising.getAdvertisingID());
            map.put("advertisingImg","<img src='"+advertising.getAdvertisingImg()+"'>");
            map.put("advertisingURL",advertising.getAdvertisingURL());
            if(advertising.getAdvertisingState()==0){
                map.put("advertisingState","上架");
            }else{
                map.put("advertisingState","下架");
            }
            list.add(map);
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }



    @ResponseBody
    @RequestMapping(value = "/outORban")
    public String outOrban(Integer userId,Integer typeId){
        int n = userService.banORout(userId,typeId);
        return n+"";
    }


    @RequestMapping(value = "/userData")
    public String tt(){
        return "user/userData";
    }

    @ResponseBody
    @RequestMapping(value = "/getGg")
    public String testGg(){
        List<Advertising> allAdvertising = advertisingService.getAllAdvertising();
        List<Advertising> s = new ArrayList<>();
        for (Advertising advertising : allAdvertising) {
            if(advertising.getAdvertisingState()==0){
                s.add(advertising);
            }
        }
        Gson gson = new Gson();
        return gson.toJson(s);
    }

    @Resource
    private EmpDao empDao;

//    @ResponseBody
//    @RequestMapping("/redis")
//    public List<Emp> getEmps(){
//        log.info("请求成功。。");
////        return empDao.getemps();
//    }

    /**
     * layui测试页面
     * @return
     */




    @RequestMapping(value = "show")
    public String test3(){
        return "user/show";
    }
    @RequestMapping(value = "show2")
    public String test4(){
        return "user/show2";
    }














    @RequestMapping(value = "/layuitest")
    public String layuitest(){
        return "user/layuitest.html";
    }
}
