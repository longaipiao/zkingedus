package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.dao.SortDao;

import java.util.List;

/**
 * 论坛类别接口
 */
public interface SortService {
    /**
     * 查询所有板块（类别）
     * @return
     */
    List<SortDao> queryAllSort();


}
