package com.zking.zkingedu.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("getcategory")
    @ResponseBody
    public Map getcategory(@Param("page") String page, @Param("limit") String limit){
        Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        Map<String,Object> map = new HashMap<>();
        List<Category> category = categoryService.getCategory();
        map.put("code","0");
        map.put("msg","");
        map.put("count",objects.getTotal());
        map.put("data",category);
        return map;
    }











    @RequestMapping("/")
    public String test(){
        return "admin/html/admin-cate";
    }
}
