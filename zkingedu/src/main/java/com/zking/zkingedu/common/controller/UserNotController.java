package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @ResponseBody
    @RequestMapping(value = "/checkPhone")
    public String checkPhone(String userPhone){
        String phone = userService.queryPhone(userPhone);
        if(phone!=null){
            return "1";
        }
        return "2";
    }

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
