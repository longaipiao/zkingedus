package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.SectionDao;
import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.service.SectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 章节接口  实现层
 */
@Service("sectionService")
public class SectionServiceImpl implements SectionService {
    @Resource
    private SectionDao sectionDao;

    /**
     * 根据课程Id获取课程章节
     * @param courseID
     * @return
     */
    @Override
    public List<Section> sections(Integer courseID) {
        return sectionDao.sections(courseID);
    }

    /**
     * 根据课程Id获取章节最大顺序号
     * @param courseID
     * @return
     */
    @Override
    public Integer maxSeq(Integer courseID) {
        return sectionDao.maxSeq(courseID);
    }

    /**
     * 添加章节
     * @param section
     * @return
     */
    @Override
    public Integer secAdd(Map section) {
        return sectionDao.secAdd(section);
    }
}
