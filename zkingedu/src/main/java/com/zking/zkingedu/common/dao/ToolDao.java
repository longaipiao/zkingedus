package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Tool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 开发者工具接口
 */
@Mapper
public interface ToolDao {
    /**
     * 查询所有,带分页
     * */
    List<Tool> getAlls();

    /**
     * 查询所有，带模糊查询
     * */
    List<Tool> getAlls1(@Param("toolName") String search);

    /**
     * 统计查询借款表的数据 + 条件（根据资源名）
     * */
    int getToolCount(Map<String, Object> mapPage);

    /**
     * 删除开发者工具
     * */
    int deleteTool(Integer toolID);

    /**
     * 编辑开发者工具
     * */
    int updateTool(Tool tool);

    /**
     * 增加一个开发者工具
     * */
    int addTool(Tool tool);

    /**
     * 根据id查询
     * */
    Tool getToolByID(Integer toolID);
}
