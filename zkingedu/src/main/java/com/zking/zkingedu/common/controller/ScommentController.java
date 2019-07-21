package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.ScommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程评论
 */
@Controller
@Slf4j
public class ScommentController {
    @Resource
    private ScommentService scommentService;

    /**
     * 查询课程评论
     * @param request
     * @return
     */
    @RequestMapping("/coms")
    @ResponseBody
    public Map coms(HttpServletRequest request){
        //接收前台的页数、每页展示数、课程ID
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        Integer page = (Integer.parseInt(request.getParameter("page"))-1)*limit;
        Integer courseID = Integer.parseInt(request.getParameter("courseID"));
        //获取课程评论、课程评论数
        List<Map> coms = scommentService.coms(page, limit, courseID);
        Integer coucom = scommentService.coucom(courseID);
        //计算页数
        Integer pages=coucom/limit;
        if (coucom%limit>0){
            pages++;
        }
        System.out.println("coms:"+coms);
        //返回的Map
        Map map=new HashMap();
        map.put("data",coms);
        map.put("pages",pages);
        return map;
    }

    /**
     * 添加课程评论
     * @param request
     * @return
     */
    @RequestMapping("/comAdd")
    @ResponseBody
    public Integer comAdd(HttpServletRequest request){
        //接收传来的课程ID、评论内容
        Integer courseID = Integer.parseInt(request.getParameter("courseID"));
        String ccontent = request.getParameter("ccontent");
        //获取用户ID
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //添加评论
        Integer n = scommentService.comAdd(courseID, user.getUserID(), ccontent);
        return n;
    }

}
