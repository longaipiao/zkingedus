package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CategoryDao;
import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<Category> results = new ArrayList<>();
        List<Category> categories = categoryDao.getCategory();//获取所有的题库类别
        if(categories!=null&categories.size()!=0){//判空
            for(int i = 0; i < categories.size(); ++i){//遍历该集合
                if(((Category)categories.get(i)).getCategoryFid()==0){//顶级类别
                    Category category = new Category();//实例化一个集合
                    category.setCategoryID(((Category)categories.get(i)).getCategoryID());
                    category.setCategoryName(((Category)categories.get(i)).getCategoryName());
                    category.setCategoryFid(((Category)categories.get(i)).getCategoryFid());
                    category.setCategoryTime(((Category)categories.get(i)).getCategoryTime());
                    category.setCategoryEid(((Category)categories.get(i)).getCategoryEid());
                    category.setCategoryRank(((Category)categories.get(i)).getCategoryRank());
                    category.setCategoryState(((Category)categories.get(i)).getCategoryState());
                    List<Category> list = new ArrayList<>();
                    for(int j = 0; j < categories.size(); ++j) {//再次遍历该集合
                        if(((Category)categories.get(i)).getCategoryID()==((Category)categories.get(j)).getCategoryFid()){
                            Category categorya = new Category();//再次实例化一个集合
                            categorya.setCategoryID(((Category)categories.get(j)).getCategoryID());
                            categorya.setCategoryName(((Category)categories.get(j)).getCategoryName());
                            categorya.setCategoryFid(((Category)categories.get(j)).getCategoryFid());
                            categorya.setCategoryTime(((Category)categories.get(j)).getCategoryTime());
                            categorya.setCategoryEid(((Category)categories.get(j)).getCategoryEid());
                            categorya.setCategoryRank(((Category)categories.get(j)).getCategoryRank());
                            categorya.setCategoryState(((Category)categories.get(j)).getCategoryState());
                            list.add(categorya);
                        }
                    }
                    category.setCategories(list);
                    results.add(category);
                }
            }
        }
        return results;
    }

    /**
     * 查询所有的题库类别
     * @return
     */
    @Override
    public List<Category> getFCategory() {
        return categoryDao.getFCategory();
    }


    /**
     * 添加题库类别
     */
    @Override
    public int addCategory(Category category) {
        return categoryDao.addCategory(category);
    }
    /**
     * 修改状态
     * @param category
     * @return
     */
    @Override
    public int updatecategoryState(Category category) {
        return categoryDao.updatecategoryState(category);
    }
    /**
     * 删除题库类别
     * @param categoryID
     * @return
     */
    @Override
    public int delcategory(Integer categoryID) {
        return categoryDao.delcategory(categoryID);
    }
}
