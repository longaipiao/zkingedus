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

    /**
     * 根据课程号查找章节
     * @param request
     * @return
     */
    @RequestMapping("/admin/secList")
    @ResponseBody
    public Map secList(HttpServletRequest request){
        //接收传来的课程Id
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        Integer page = (Integer.parseInt(request.getParameter("page"))-1)*limit;

        Map query=new HashMap();
        query.put("courseId",courseId);
        query.put("page",page);
        query.put("limit",limit);

        //根据课程Id获取获取章节
        List<Section> sections = sectionService.secList(query);
//        System.out.println("limit:"+limit+"  page:"+page+"  sections："+sections);

        //获取章节总数量
        Integer count = sectionService.couSec(courseId);

        //返回的Map
        Map map=new HashMap();
        map.put("code",0);
        map.put("data",sections);
        map.put("count",count);

        return map;
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

    /**
     * 根据章节Id删除章节（删单个及批量删除）
     * @param request
     * @return
     */
    @RequestMapping("/admin/secDel")
    @ResponseBody
    public Integer secDel(HttpServletRequest request){
        //接收前台传来的章节Id，课程ID
        String sectionIDs = request.getParameter("sectionIDs");
        Integer courseId=Integer.parseInt(request.getParameter("courseId"));
//        System.out.println(sectionIDs);
        //将前台传来的字符串切割成数组
        String[] idList = sectionIDs.split(",");
        Integer n=0;

        try {
            //循环数组进行章节删除
            for (int i=0;i<idList.length;i++){
                    //调用根据Id删除章节方法
                    sectionService.secDel(Integer.parseInt(idList[i]));
                    n++;
                }

            //删除成功后重新进行排序
            //获取所在章节所有章节
            List<Section> sections = sectionService.sections(courseId);
            //进行循环
            for(int i=0;i<sections.size();i++){
                //获取所有章节的ID进行顺序修改
                sectionService.secUpSeq(sections.get(i).getSectionID(), i+1);
            }
        }catch (Exception e){
                e.printStackTrace();
                return -1;
            }

        return n;
    }

    /**
     * 修改章节信息
     * @param request
     * @return
     */
    @RequestMapping("/admin/secUpd")
    @ResponseBody
    public Integer secUpd(HttpServletRequest request){
        String field = request.getParameter("field");
        String value = request.getParameter("value");
        String sectionID = request.getParameter("sectionID");
        //判断修改哪列数据
        if ("sectionInte".equals(field)){
            field="section_inte";
        }else if ("sectionName".equals(field)){
            field="section_name";
        }
        //调用修改章节信息方法
        Integer n = sectionService.secUpd(sectionID, field, value);
//        System.out.println("field:"+field+"  value:"+value+"  sectionID:"+sectionID);
        return n;
    }

    /**
     * 章节上移
     * @param request
     * @return
     */
    @RequestMapping("/admin/MoveUp")
    @ResponseBody
    public Integer secMoveUp(HttpServletRequest request){
        //获取传来的顺序号
        Integer sectionSeq = Integer.parseInt(request.getParameter("sectionSeq"));
        Integer sectionCid = Integer.parseInt(request.getParameter("sectionCid"));
        //获取章节信息
        Section section = sectionService.section(sectionCid, sectionSeq);
        //获取上一条章节信息
        Section section1 = sectionService.section(sectionCid, sectionSeq - 1);
//        System.out.println("section："+section+"  section1："+section1);
        Integer n=0;
        try {
            //章节上移
            n = sectionService.secUpSeq(section.getSectionID(), sectionSeq - 1);
            //章节下移
            n+=sectionService.secUpSeq(section1.getSectionID(),sectionSeq);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return n;
    }

    /**
     * 章节下移
     * @param request
     * @return
     */
    @RequestMapping("/admin/down")
    @ResponseBody
    public Integer secdown(HttpServletRequest request){
        //获取传来的顺序号
        Integer sectionSeq = Integer.parseInt(request.getParameter("sectionSeq"));
        Integer sectionCid = Integer.parseInt(request.getParameter("sectionCid"));
        //获取章节信息
        Section section = sectionService.section(sectionCid, sectionSeq);
        //获取下一条章节信息
        Section section1 = sectionService.section(sectionCid, sectionSeq + 1);
//        System.out.println("section："+section+"  section1："+section1);
        Integer n=0;
        try {
            //章节上移
            n = sectionService.secUpSeq(section.getSectionID(), sectionSeq + 1);
            //章节下移
            n+=sectionService.secUpSeq(section1.getSectionID(),sectionSeq);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return n;
    }

}
