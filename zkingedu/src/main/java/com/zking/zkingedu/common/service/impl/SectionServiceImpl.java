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
     * 根据课程Id获取课程章节及分页
     * @param query
     * @return
     */
    @Override
    public List<Section> secList(Map query) {
        return sectionDao.secList(query);
    }

    /**
     * 获取章节总数量
     * @param courseID
     * @return
     */
    @Override
    public Integer couSec(Integer courseID) {
        return sectionDao.couSec(courseID);
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

    /**
     * 根据章节Id删除章节
     * @param sectionID
     * @return
     */
    @Override
    public Integer secDel(Integer sectionID) {
        return sectionDao.secDel(sectionID);
    }

    /**
     * 根据章节Id修改章节信息
     * @param sectionID
     * @param field
     * @param value
     * @return
     */
    @Override
    public Integer secUpd(String sectionID, String field, String value) {
        return sectionDao.secUpd(sectionID,field,value);
    }

    /**
     * 根据章节ID修改顺序号
     * @param sectionID
     * @param sectionSeq
     * @return
     */
    @Override
    public Integer secUpSeq(Integer sectionID, Integer sectionSeq) {
        return sectionDao.secUpSeq(sectionID,sectionSeq);
    }

    /**
     * 根据课程ID及章节顺序号获取章节
     * @param sectionCid
     * @param sectionSeq
     * @return
     */
    @Override
    public Section section(Integer sectionCid, Integer sectionSeq) {
        return sectionDao.section(sectionCid,sectionSeq);
    }

}
