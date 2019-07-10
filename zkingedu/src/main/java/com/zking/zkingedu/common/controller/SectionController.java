package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class SectionController {
    @Resource
    private SectionService sectionService;

    @RequestMapping("/admin/secList")
    @ResponseBody
    public List<Section> secList(HttpServletRequest request){
        //接收传来的课程Id
//        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        //根据课程Id获取获取章节
//        List<Section> sections = sectionService.sections(courseId);
//        System.out.println(request.getParameter("courseId"));

        return null;
    }

    /**
     * 添加课程章节
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/secAdd",method = RequestMethod.POST)
    @ResponseBody
    public Integer secAdd(HttpServletRequest request){
        //接收前台传来的值
        Integer sectionCid = Integer.parseInt(request.getParameter("sectionCid"));
        Integer sectionInte = Integer.parseInt(request.getParameter("sectionInte"));
        String videoUrl = request.getParameter("videoUrl");
        String sectionName = request.getParameter("title");
        //获取章节最大顺序号
        Integer sectionSeq = sectionService.maxSeq(sectionCid);
        sectionSeq+=1;

        Map section=new HashMap();
        section.put("sectionCid",sectionCid);
        section.put("sectionInte",sectionInte);
        section.put("videoUrl",videoUrl);
        section.put("sectionName",sectionName);
        section.put("sectionSeq",sectionSeq);

        //添加章节
        Integer n = sectionService.secAdd(section);

//        System.out.println(section+"  添加返回："+n);

        return n;
    }

}
