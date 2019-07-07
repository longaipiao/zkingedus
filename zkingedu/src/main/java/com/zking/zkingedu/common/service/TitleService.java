package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Title;

import java.util.List;

/**
 * 题目接口
 */
public interface TitleService {
    /**
     * 查询所有题库
     * @param title
     * @return
     */
    List<Title> gettitles(Title title);
}
