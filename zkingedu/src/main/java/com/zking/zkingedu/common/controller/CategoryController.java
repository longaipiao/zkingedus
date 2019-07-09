package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //展示题库类别数据
    @RequestMapping("/getcategory")
    @ResponseBody
    public List getcategory(){
        return categoryService.getCategoryall();
    }


//查询所有父题库类别
    @RequestMapping("/getfcategory")
    @ResponseBody
    public List getfcategory(){
        return categoryService.getFCategory();
    }


    //添加题库类别
    @RequestMapping("/addcategory")
    @ResponseBody
    public String addcategory(Category category){
        String nowDate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());//当前时间
        category.setCategoryTime(nowDate);
        category.setCategoryRank(1);
        category.setCategoryState(0);
        categoryService.addCategory(category);
        return "ok";
    }

    //修改题库状态
    @RequestMapping("/updatecategory")
    @ResponseBody
    public String updatecategoryState(Category category){
        if(category.getCategoryID()!=null&category.getCategoryState()!=null){//状态不为空就修改状态
            if(category.getCategoryState()==0){
                category.setCategoryState(1);
                categoryService.updatecategoryState(category);//调用修改
            }
            else{
                category.setCategoryState(0);
                categoryService.updatecategoryState(category);//调用修改
            }
        }
        else if(category.getCategoryID()!=null&category.getCategoryName()!=null){//名字不为空就修改名字
            categoryService.updatecategoryState(category);
        }
        return "ok";
    }

    //删除题库类别
    @RequestMapping("/delcategory")
    @ResponseBody
    public String delcategory(String categoryID){
        if(categoryID==null){
            return "no";
        }
        categoryService.delcategory(Integer.parseInt(categoryID));
        return "ok";
    }
}
