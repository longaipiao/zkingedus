package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 论坛类别接口
 */
public interface SortDao {

    /**
     * 查询所有板块（类别）
     * @return
     */
    List<Sort> queryAllSort();

    List<Sort> queryAllSortU();

    /**
     * 添加板块类别
     * @param sort
     * @return
     */
    int addSort(@Param("sort")Sort sort);

    /**
     * 修改板块名字
     * @param sort
     * @return
     */
    int updateSort(@Param("sort") Sort sort);

    /**
     * 根据名字查询类别
     * @param sortName
     * @return
     */
    String querySortByName(String sortName);

    /**
     * 启用OR停用类别
     * @param type
     * @param sortID
     * @return
     */
    int sortBan(@Param("type") Integer type,@Param("sortID") Integer sortID);


}
