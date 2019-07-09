package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题库类别接口
 */
public interface CategoryDao {
    /**
     * 查询所有的题库类别
     * @return
     */
    List<Category> getCategoryall();


    /**
     * 查询题库类别非停用
     * @return
     */
    List<Category> getCategory();

    /**
     * 查询所有的父体系
     */
    List<Category> getFCategory();

    /**
     * 添加题库类别
     */
    int addCategory(Category category);

    /**
     * 修改状态
     * @param category
     * @return
     */
    int updatecategoryState(Category category);

    /**
     * 删除题库类别
     * @param categoryID
     * @return
     */
    int delcategory(@Param("categoryID") Integer categoryID);

    /**
     * 按ID查询题库类别
     * @param categoryID
     * @return
     */
    Category getcat(@Param("categoryID") Integer categoryID);

    /**
     * 根据父题库ID查询子题库字段
     * @param categoryFID
     * @return
     */
    List<Category> gettikuzitype(@Param("categoryFID") Integer categoryFID);
}
