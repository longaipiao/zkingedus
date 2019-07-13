package com.zking.zkingedu.common.controller;

import com.google.gson.Gson;
import com.zking.zkingedu.common.dao.SortDao;
import com.zking.zkingedu.common.model.CourseType;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.model.Sort;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.CourseTypeService;
import com.zking.zkingedu.common.service.PostService;
import com.zking.zkingedu.common.service.SortService;
import com.zking.zkingedu.common.service.TcommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BbsController {
    @Autowired
    private PostService postService;
    @Autowired
    private SortService sortService;
    @Autowired
    private TcommentService tcommentService;
    @Resource
    private CourseTypeService courseTypeService;


    @RequestMapping(value = "/bbsIndex")
    public String test1(String post_name,String checkType,HttpServletRequest request){
        if("搜帖子".equals(checkType)){
            request.setAttribute("post_name",post_name);
            //搜索框里的值
            request.setAttribute("courseName",post_name);
            return "user/bbsIndex";
        }else if("搜课程".equals(checkType)){
            //获取所有课程类别
            List<CourseType> courseTypes = courseTypeService.courseTypes();
            request.setAttribute("courseTypes",courseTypes);
            //将课程是否免费、课程分类，课程搜索名字参数存入request
            request.setAttribute("free",2);
            request.setAttribute("Tid",0);
            //判断搜索框里的值
            if(post_name==null||"".equals(post_name.trim())){
                post_name="0";
            }
            request.setAttribute("courseName",post_name);
            //搜索的类型
            request.setAttribute("checkType",checkType);
            return "user/courses/index.html";
        }
        return "user/bbsIndex";
    }

    /**
     * 分页并按类别查询帖子
     * @param page
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pagePost")
    public String pagePost(Integer page,String type,String post_name){
        List<Map<String, Object>> list;
        Integer start = (page-1)*5;
        int cpage=0;
        if(type!=null&&type.length()!=0&&!type.equals("0")){
            list = postService.queryPagePostByType(start, 5,type);
            Integer count = postService.queryAllPostByType(type).size();
            cpage = count/5;
            if(count%5!=0){
                cpage+=1;
            }
        }else if(post_name!=null&&post_name.length()!=0){
            Integer count = postService.queryAllPostByNamePage(post_name,null, null).size();
            cpage = count/5;
            if(count%5!=0){
                cpage+=1;
            }
            list = postService.queryAllPostByNamePage(post_name,start, 5);
        } else{
            Integer count = postService.queryAllPost().size();
            cpage = count/5;
            if(count%5!=0){
                cpage+=1;
            }
           list = postService.queryPagePost(start, 5);
        }
        //计算总评论数
        for (Map<String, Object> map : list) {
            Integer post_id = Integer.parseInt(map.get("post_id1").toString());
            List<Map<String, Object>> list1 = tcommentService.queryTcomment(post_id);//所有评论
            map.put("tcommentCount",list1.size());
        }

        //计算总页数
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        map.put("apage",cpage);
        map.put("count",cpage);
        map.put("types",sortService.queryAllSort());
        if(type!=null&&type.length()!=0){
            map.put("type",type);
        }

        Gson gson = new Gson();
        String str = gson.toJson(map);
        return str;
    }



    /**
     * 根据id查找帖子并查看该帖子下的所有评论和回复
     * @param post_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkBbs/{post_id}")
    public String checkBbs(@PathVariable("post_id")Integer post_id, HttpServletRequest request){
        //增加浏览量
        postService.pageView(post_id);


        Post post = new Post();
        post.setPostID(post_id);
        List<Map<String, Object>> list = postService.queryPostByid(post);//帖子
        List<Map<String, Object>> list1 = tcommentService.queryTcomment(post_id);//所有评论

        List<Map<String, Object>> tcomments = new ArrayList<>();//帖子评论集合
        List<Map<String, Object>> tcommentsUser = new ArrayList<>();//评论回复集合
        //筛选评论的回复
        for (Map<String, Object> map : list1) {
            int tcomment_fid = Integer.parseInt(map.get("tcomment_fid").toString());
            if(tcomment_fid==0){
                tcomments.add(map);
            }else{
                tcommentsUser.add(map);
            }
        }

        //将评论的回复放入帖子评论的下面
        for (Map<String, Object> tcomment : tcomments) {
            //定义评论下的回复集合
            List<Map<String, Object>> userTcomment = new ArrayList<>();
            int tcomment_id = Integer.parseInt(tcomment.get("tcomment_id").toString());
            //定义回复计数
            int count = 0;
            for (Map<String, Object> map : tcommentsUser) {
                int tcomment_fid = Integer.parseInt(map.get("tcomment_fid").toString());

                //如果评论的回复等于此评论的ID
                if(tcomment_id==tcomment_fid){
                    count++;
                    map.put("HuserName","karabo");
                    //一级回复
                    userTcomment.add(map);
                    int tcomment_id2 = Integer.parseInt(map.get("tcomment_id").toString());
                    //再遍历一次查看回复中的回复
                    for (Map<String, Object> stringObjectMap : tcommentsUser) {
                        int tcomment_fid2 = Integer.parseInt(stringObjectMap.get("tcomment_fid").toString());
                        if(tcomment_id2==tcomment_fid2){
                            stringObjectMap.put("HuserName",map.get("user_name"));
                            //二级回复
                            userTcomment.add(stringObjectMap);
                        }
                    }
                }
            }
            //将回复的集合添加的评论的属性中
            tcomment.put("userTcomments",userTcomment);
            tcomment.put("count",count);

        }

        //查询该用户是否收藏了该帖子
        Integer user_id = 41;
        Map<String, Object> map = new HashMap<>();//收藏点赞
        map.put("collection","");
        map.put("give","");
        if(tcommentService.queryCollection(post_id, user_id)!=null){
            map.put("collection","yes");
        }
        //查询该用户是否点赞了该帖子
        if(tcommentService.queryGiveById(post_id, user_id)!=null){
            map.put("give","yes");
        }


        //获取点赞数和收藏数
        Integer giveNum = tcommentService.queryCountGive(post_id);
        Integer postNum=tcommentService.queryCountPost(post_id);
        map.put("giveNum",giveNum);
        map.put("postNum",postNum);
        map.put("countNum",tcomments.size());
        map.put("tcommentCountNum",list1.size());

        request.setAttribute("cORg",map);
        request.setAttribute("post",list.get(0));
        request.setAttribute("tcomments",tcomments);
        return "user/show2";
    }







    /**
     * 查看所有帖子
     * @param request
     * @return
     */
    @RequestMapping(value = "/post")
    public String checkType(HttpServletRequest request){
        request.setAttribute("sorts",sortService.queryAllSort());
        return "user/post";
    }

    /**
     * 发布帖子
     * @param post
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addBbs")
    public String addPost(Post post,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        post.setPostUid(user.getUserID());
        post.setPostTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        post.setPostNum(0);
        return postService.addPost(post)>0?"1":"0";
    }

    /**
     * 收藏or取消收藏
     * @param post_id
     * @param user_id
     * @param type_id
     * @param cTime
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addConllection")
    public String addConllection(Integer post_id, Integer user_id, Integer type_id, String cTime,Integer type){
        int n=0;
        String resultMsg="";
        if(type==1){//如果是收藏类型的操作
            cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            type_id = 1;
            user_id = 41;
            n = tcommentService.addCollection(post_id,user_id,type_id,cTime);
            if(n>0){
                resultMsg = "addCollection";
            }else{
                resultMsg = "addCError";
            }
        }else{
            user_id=41;
            n = tcommentService.deleteConllection(post_id,user_id);
            if(n>0){
                resultMsg = "delCollection";
            }else{
                resultMsg = "delCError";
            }
        }
        System.out.println(resultMsg);
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping(value = "/addGive")
    public String addGive(Integer post_id,Integer type){
        Integer user_id = 41;
        String resultMsg="Error";
        if(type==1){
            if(tcommentService.addGive(post_id,user_id,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))>0){
                resultMsg = "addGive";
            }
        }else{
            if(tcommentService.delGive(post_id,user_id)>0){
                resultMsg = "delGive";
            }
        }
        return resultMsg;
    }

    /**
     * 后台获取帖子数据
     * @return
     */
    @RequestMapping(value = "/postData")
    public String queryPost(HttpServletRequest request){
        //获取所有类型，跳转后给搜索下拉框赋值
        request.setAttribute("sorts",sortService.queryAllSort());
        System.out.println(request.getAttribute("sorts"));
        return "user/postData";
    }

    /**
     * 后台获取帖子数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllPostH")
    public String queryAllPostH(){
        List<Map<String, Object>> list = postService.queryAllPost();
        for (Map<String, Object> map : list) {
            if(Integer.parseInt(map.get("post_state").toString())==0){
                map.put("post_state","正常");
            }else{
                map.put("post_state","封禁");
            }
        }
        Gson gson = new Gson();
        String str = gson.toJson(list);
        return str;
    }

    /**
     * 后台获取帖子数据（搜索）
     * @param type
     * @param value
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllPostHcheck")
    public String queryAllPostHs(String type,String value,HttpServletRequest request){
        List<Map<String, Object>> list = postService.queryAllPostByTypeTo(type, value);
        for (Map<String, Object> map : list) {
            if(Integer.parseInt(map.get("post_state").toString())==0){
                map.put("post_state","正常");
            }else{
                map.put("post_state","封禁");
            }
        }
        Gson gson = new Gson();
        String str = gson.toJson(list);
        request.setAttribute("type1",type);
        request.setAttribute("value1",value);
        return str;
    }

    /**
     * 封禁帖子
     * @param post_id
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/banPost")
    public String outOrban(Integer post_id,Integer type){
        int n = postService.banPost(post_id, type);
        return n+"";
    }


    /**
     * 后台获取类别数据
     * @return
     */
    @RequestMapping(value = "/getSorts")
    @ResponseBody
    public Map getSorts(){
        List<Sort> sorts = sortService.queryAllSort();
        List<Map<String,Object>> map = new ArrayList<>();
        for (Sort sort : sorts) {
            Map m = new HashMap();
            m.put("sortID",sort.getSortID());
            m.put("sortName",sort.getSortName());
            if(sort.getSortState()==0){
                m.put("sortState","启用中");
            }else{
                m.put("sortState","停用中");
            }
            map.add(m);
        }
        Map m1 = new HashMap();
        m1.put("code",0);
        m1.put("msg","");
        m1.put("count",map.size());
        m1.put("data",map);
        Gson gson = new Gson();
        String str = gson.toJson(m1);
        return m1;
    }

    /**
     * t跳转至后台页面
     * @return
     */
    @RequestMapping(value = "/sortData")
    public String t(){
        return "user/sortData";
    }

    /**
     * 添加板块
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addSort")
    public String addSort(String sortName){
        if(sortService.querySortByName(sortName)!=null){
            return "2";
        }
        Sort sort = new Sort();
        sort.setSortName(sortName);
        return sortService.addSort(sort)+"";
    }

    /**
     * 修改板块
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSort")
    public String updateSort(Integer sortID,String sortName){
        if(sortService.querySortByName(sortName)!=null){
            return "2";
        }
        Sort sort = new Sort();
        sort.setSortID(sortID);
        sort.setSortName(sortName);
        return sortService.updateSort(sort)+"";
    }

    /**
     * 停用or启用
     * @param type
     * @param sortID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sortBan")
    public String sortBan(Integer type,Integer sortID){
        return sortService.sortBan(type, sortID)+"";
    }

}
