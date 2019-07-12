package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.ToolDao;
import com.zking.zkingedu.common.model.Tool;
import com.zking.zkingedu.common.service.ToolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 开发者工具接口
 */
@Service("toolService")
public class ToolServiceImpl implements ToolService {

    @Resource
    private ToolDao toolDao;

    @Override
    public List<Tool> getAlls() {
        return toolDao.getAlls();
    }

    @Override
    public List<Tool> getAlls1(String search) {
        return toolDao.getAlls1(search);
    }

    @Override
    public int getToolCount(Map<String, Object> mapPage) {
        return toolDao.getToolCount(mapPage);
    }

    @Override
    public int deleteTool(Integer toolID) {
        return toolDao.deleteTool(toolID);
    }

    @Override
    public int updateTool(Tool tool) {
        return toolDao.updateTool(tool);
    }

    @Override
    public int addTool(Tool tool) {
        return toolDao.addTool(tool);
    }

    @Override
    public Tool getToolByID(Integer toolID) {
        return toolDao.getToolByID(toolID);
    }
}
