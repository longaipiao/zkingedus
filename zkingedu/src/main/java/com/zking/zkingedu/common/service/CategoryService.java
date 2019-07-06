package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题库类别接口
 */
public interface CategoryService {
    /**
     * 查询所有的题库类别
     * @return
     */
    List<Category> getCategory();

    /**
     * 查询所有的题库类别
     * @return
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
}
