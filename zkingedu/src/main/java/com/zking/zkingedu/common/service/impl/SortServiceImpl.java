package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.SortDao;
import com.zking.zkingedu.common.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 论坛类别接口  服务层实现
 */
@Service
public class SortServiceImpl implements SortService {
    @Autowired
    private SortDao sortDao;
    @Override
    public List<SortDao> queryAllSort() {
        return sortDao.queryAllSort();
    }
}
