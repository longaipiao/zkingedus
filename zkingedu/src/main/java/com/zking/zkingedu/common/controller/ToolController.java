package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Tool;
import com.zking.zkingedu.common.service.ToolService;
import com.zking.zkingedu.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
public class ToolController {
    //方法接口
    @Autowired
    private ToolService toolService;

    //对象
    @Resource
    private Tool tool;

    /**
     * 进入前台开发者工具
     * @param
     * @return ModelAndView
     */
    @RequestMapping(value = "/getTools", method = RequestMethod.GET)
    public ModelAndView getTools() throws Exception{
//        System.out.println("开发者工具");

        //从数据库里面获取值
        List<Tool> tools = toolService.getAlls();
//        for (tool tool: tools) {
//            System.out.println(tool);
//        }
        ModelAndView mv = new ModelAndView();
        //路径
        mv.setViewName("user/developer/index");
        //转发的键与值
        mv.addObject("tools",tools);
        return mv;
    }

    /**
     * 后台测试（跳页面）
     * @param
     * @return 路径
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download() throws Exception{
        return "admin/html/download-list";
    }

    /**
     * 进入后台开发者工具
     * @param
     * @return map
     */
    @ResponseBody
    @RequestMapping(value = "/setTool", method = RequestMethod.GET)
    public Map<String,Object> setTool(HttpServletRequest request,Integer page,Integer limit) throws Exception{
//        System.out.println( "后台开发者工具");

        //获取文本框的值
        String search = request.getParameter("demoReload");
        //分页
        Page<Tool> toolPage = PageHelper.startPage(page, limit);
        //存入list集合中
        List<Tool> toolList = toolService.getAlls1(search);
//        for (tool tool: toolList) {
//            System.out.println(tool);
//        }

        //匹配layui json数据格式
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("data", toolList);
        map.put("code", 0);
        map.put("msg", "请求成功");
        map.put("count", toolPage.getTotal());
        return map;
    }

    /**
     * 删除开发者工具
     * @param
     * @return 路径
     */
    @RequestMapping(value = "/deleteTool", method = RequestMethod.POST)
    public String deleteTool(HttpServletRequest request) throws Exception{
        //获取要删除的开发者工具的id
        String id = request.getParameter("toolID");
//        System.out.println("hhhh   拿到id：" + id);
        //调用开发者工具的删除方法
        toolService.deleteTool(Integer.parseInt(id));
        //跳转
        return "admin/html/download-list";
    }

    /**
     * 根据id获取数据
     * @return tool
     */
    @RequestMapping(value = "/getToolByID",method = RequestMethod.GET)
    public ModelAndView getToolByID(HttpServletRequest request){
        //获取id
        String toolID = request.getParameter("toolIDs");
//        System.out.println("工具id：     "+toolID);
        //调用方法
        Tool tool1 = toolService.getToolByID(Integer.parseInt(toolID));
        ModelAndView mv = new ModelAndView();
        //路径
        mv.setViewName("admin/tool/tool-edit");
        //传值到修改界面
        mv.addObject("tool1",tool1);
        return mv;
    }

    /**
     * 编辑开发者工具
     * @param
     * @return 路径
     */
    @ResponseBody
    @RequestMapping(value = "/updateTool")
    public String updateTool(HttpServletRequest request,Tool tool) throws Exception{
        //获取开发者工具的名称
//        String toolName = request.getParameter("toolName");
//        //获取开发者工具的介绍
//        String toolDescribe = request.getParameter("toolDescribe");
//        //获取开发者工具的路径
//        String toolUrl = request.getParameter("toolUrl");
//        //获取开发者工具的图片
//        String toolImg = request.getParameter("toolImg");
//        //获取开发者工具的id
//        String toolID = request.getParameter("toolID");
//
//        tool.setToolName(toolName);//名字
//        tool.setToolDescribe(toolDescribe);//介绍
//        tool.setToolImg(toolImg);//图片
//        tool.setToolURL(toolUrl);//路径
//        tool.setToolID(Integer.parseInt(toolID));//id
//        //获取系统当前时间
        String time  = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        tool.setToolTime(time);//时间
        System.out.println("       "+tool);
//        //调用开发者工具的编辑方法
        toolService.updateTool(tool);
        //跳页面
        return "admin/html/download-list";
    }

    /**
     * 增加开发者工具
     * @param
     * @return 路径
     */

    @ResponseBody
    @RequestMapping(value = "/addTools")
    public String addTools(Tool tool){
        //获取系统当前时间
        tool.setToolTime(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));//时间

        //调用开发者工具的增加方法
        toolService.addTool(tool);
        return "admin/html/download-list";
    }

    /**
     * 跳增加页面
     * @param
     * @param
     * @return 路径
     */
    @RequestMapping(value = "/test")
    public String test() throws Exception{
        return "admin/tool/tool-add";
    }

    /**
     * 跳编辑页面
     * @return 路径
     */
    @RequestMapping(value = "/edit")
    public String edit() throws Exception{
        return "admin/tool/tool-edit";
    }

    /**
     * 开发工具图片的修改
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value="/tool/addToolImg")
    @ResponseBody
    public Map<String,Object> addToolImg(@RequestParam("file") MultipartFile file , HttpServletRequest request) {
        System.out.println("修改体系图片的方法");
        //获取传来的体系Id
        String sid = request.getParameter("sid");
        //存放图片路径
        String pathString = null;
        String DBImgPath = null;
        //获取当前时间
        String time=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        if(file!=null) {
            //服务器地址
            pathString = "F:/a/zkingedu/src/main/resources/static/user/img/"+time+"_" +file.getOriginalFilename();
            //存入数据库的地址
            DBImgPath = "/user/img/"+time+"_" +file.getOriginalFilename();
        }
        try {
            File files=new File(pathString);
            //打印查看上传路径
            if(!files.getParentFile().exists()){
                files.getParentFile().mkdirs();
            }
            file.transferTo(files);

            log.info("pathString:"+pathString);

            Map<String,Object> maps = new HashMap<>();
            maps.put("imgname",DBImgPath);

            //图片上传成功返回参数
            return maps;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //上传失败返回参数
            return null;
        }
    }

}
