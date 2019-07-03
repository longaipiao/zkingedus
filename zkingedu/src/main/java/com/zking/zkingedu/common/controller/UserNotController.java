package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import com.zking.zkingedu.common.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@Slf4j
@RequestMapping(value = "user")
@Transactional
public class UserNotController {
    @Autowired
    private UserService userService;

    /**
     * 注册方法
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/zc")
    public String zc(User user){
        user.setUserName(getRandomJianHan(3));
        user.setUserRegTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        user.setUserImg("default.jpg");
        user.setUserIntegrsl(0);
        user.setUserState(0);
        user.setUserIP("127.0.0.1");
        return userService.addUser(user)>0?"注册成功":"注册失败";
    }

    /**
     * 检查手机号是否已被注册
     * @param userPhone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkPhone")
    public String checkPhone(String userPhone){
        String phone = userService.queryPhone(userPhone);
        if(phone!=null){
            return "1";
        }
        return "2";
    }

    /**
     * 登录方法
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginCheck")
    public String loginCheck(User user, HttpServletRequest request){

        User user1 = userService.userLogin(user);
        if(user1!=null){
            request.getSession().setAttribute("user",user1);
            int n = userService.updateAddressAndTime(IpAddress.getIpAddr(request),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()),user1.getUserID());
            if(n>0){
                return "1";
            }
        }
        return "2";
    }

    /**
     * 跳转找回密码界面
     * @return
     */
    @RequestMapping(value = "zhPwd")
    public String zhpwd(){
        return "user/zhPwd";
    }

    /**
     * 检查账号或邮箱是否有在平台注册或绑定账号
     * @param user
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkPhoneOrEmail")
    public String checkPhoneOrEmail(User user,Integer type){
        User u;
        try {
            if(type==1){
                u = userService.queryUserByEmailOrPhone(user);
                return u==null?"此账号不存在":"1";
            }else{
                u = userService.queryUserByEmailOrPhone(user);
                return u==null?"此邮箱未绑定账号":"1";
            }
        }catch (Exception e){
            System.out.println("错误：checkPhoneOrEmailError");
            e.printStackTrace();
        }
       return "error";
    }

    /**
     * 找回密码方法
     * @param pORe
     * @param pwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "zhPassword")
    public String updatePwdEmailORPhone(String pORe,String pwd){

        int n=0;
        if(pORe.contains("@")){
            n = userService.updatePwdByEmail(pORe,pwd);
        }else{

            n = userService.updatePwdByPhone(pORe,pwd);
        }
        return n+"";
    }

    /**
     * 发送邮件方法
     * @param Email
     * @param code
     * @return
     */
    @RequestMapping(value = "/sendMail")
    @ResponseBody
    public String sEmail(String Email,String code){
        System.out.println(Email+":::::"+code);
        try {
            MailUtil.configMail(Email, code);
            return "1";
        } catch (Exception e) {
            System.out.println("发送邮件错误");
            e.printStackTrace();
        }
        return "2";
    }










    /**
     * 随机生成中文用户名方法
     * @param len
     * @return
     */
    public static String getRandomJianHan(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());

            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }


}
