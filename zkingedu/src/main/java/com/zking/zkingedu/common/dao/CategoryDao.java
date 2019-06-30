package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Category;

import java.util.List;

/**
 * 题库类别接口
 */
public interface CategoryDao {
    /**
     * 查询所有的题库类别
     * @return
     */
    List<Category> getCategory();
}
