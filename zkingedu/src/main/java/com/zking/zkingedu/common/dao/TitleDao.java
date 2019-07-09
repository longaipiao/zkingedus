package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.model.Title;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目接口
 */
public interface TitleDao {
    /**
     * 查询所有题库
     * @param title
     * @return
     */
    List<Title> gettitles(Title title);


}
