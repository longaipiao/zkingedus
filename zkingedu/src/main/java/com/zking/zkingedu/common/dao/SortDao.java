package com.zking.zkingedu.common.dao;

import java.util.List;

/**
 * 论坛类别接口
 */
public interface SortDao {

    /**
     * 查询所有板块（类别）
     * @return
     */
    List<SortDao> queryAllSort();

}
