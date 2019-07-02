package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CategoryDao;
import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题库类别接口服务层
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;


    /**
     * 查询所有的题库类别
     * @return
     */
    @Override
    public List<Category> getCategory() {
        return categoryDao.getCategory();
    }
}
