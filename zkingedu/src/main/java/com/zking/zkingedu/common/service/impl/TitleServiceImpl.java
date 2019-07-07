package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.TitleDao;
import com.zking.zkingedu.common.model.Title;
import com.zking.zkingedu.common.service.TitleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目接口  服务实现层
 */
@Service("titleService")
public class TitleServiceImpl implements TitleService {
    @Resource
    private TitleDao titleDao;


    /**
     * 查询所有题库
     * @param title
     * @return
     */
    @Override
    public List<Title> gettitles(Title title) {
        return titleDao.gettitles(title);
    }
}
