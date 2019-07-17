package com.zking.zkingedu.common.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class QQLogin {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "binding")
    public String loginQq(String img, String qname, String openId, HttpServletRequest request){
        System.out.println("来了");
        return "user/binding";
    }

    @RequestMapping(value = "/qqLogin")
    public String  LoginCallback(HttpServletRequest request){
        HttpSession session = request.getSession();
        try {
            AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
            System.out.println("Token:"+accessTokenObj.getAccessToken());
            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
            } else {
                //1.根据code获取access_token
                String token = accessTokenObj.getAccessToken();
                //2.根据access_token获得openId
                OpenID openIDobj = new OpenID(token);
                String userOpenID = openIDobj.getUserOpenID();
                //3.去数据库查找有没有openId如果有，登陆过，没有则是新用户
                com.qq.connect.api.qzone.UserInfo qzoneUserInfo = new com.qq.connect.api.qzone.UserInfo(token, userOpenID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                System.out.println("openid:"+userOpenID+"————用户昵称："+userInfoBean.getNickname());
                System.out.println("用户昵称："+userInfoBean.getNickname());
                 userInfoBean.getAvatar().getAvatarURL100();
                System.out.println("3:"+userInfoBean.getAvatar());
                //4.根据openId和access_Token获取用户信息
                //查询数据库里面有没有这个openId如果有把这个用户的信息拿出来没有就添加
                User qquser = userService.queryUserByOpenid(userOpenID);
                if(qquser!=null){
                    qquser.setUserIP(IpAddress.getIpAddr(request));
                    qquser.setUserLastTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    userService.updateAddressAndTime(IpAddress.getIpAddr(request),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()),qquser.getUserID());
                    session.setAttribute("user",qquser);
                    return "user/index";
                }
                qquser = new User();

                qquser.setUserName(userInfoBean.getNickname());
                qquser.setUserImg(userInfoBean.getAvatar().getAvatarURL100());
                qquser.setUserOpenID(userOpenID);
                request.getSession().setAttribute("qquser",qquser);
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "user/binding";
    }


    /**
     * 用户点击qq联合登陆
     * @return
     */
    @RequestMapping("qqCheck")
    public String requestQQLogin(HttpServletRequest request) throws QQConnectException {
        //自动组装qq登陆连接，重定向到qq登陆页面
        String authorizeURL = new Oauth().getAuthorizeURL(request);
        return "redirect:"+authorizeURL;
    }

    /**
     * 添加QQ用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("addQQUser")
    public String addQQuser(User user,HttpServletRequest request){
        int n = 0;
        try {
            System.out.println(user);
            user.setUserIntegrsl(0);
            user.setUserState(0);
            user.setUserRegTime(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));
            n = userService.addQqLogin(user);
        }catch (Exception e){
            System.out.println("qq用户添加错误");
            e.printStackTrace();
        }

        return n+"";
    }
}
