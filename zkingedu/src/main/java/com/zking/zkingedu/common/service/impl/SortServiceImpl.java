package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.SortDao;
import com.zking.zkingedu.common.model.Sort;
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
    public List<Sort> queryAllSort() {
        return sortDao.queryAllSort();
    }

    @Override
    public int addSort(Sort sort) {
        return sortDao.addSort(sort);
    }

    @Override
    public int updateSort(Sort sort) {
        return sortDao.updateSort(sort);
    }

    @Override
    public String querySortByName(String sortName) {
        return sortDao.querySortByName(sortName);
    }

    @Override
    public int sortBan(Integer type, Integer sortID) {
        return sortDao.sortBan(type, sortID);
    }
}
