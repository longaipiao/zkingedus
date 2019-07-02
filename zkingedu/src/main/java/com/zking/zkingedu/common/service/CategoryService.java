package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Category;

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
}
