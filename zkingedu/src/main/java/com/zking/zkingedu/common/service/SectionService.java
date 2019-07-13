package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Section;

import java.util.List;
import java.util.Map;

/**
 * 章节接口
 */
public interface SectionService {
    /**
     * 根据课程Id获取课程章节
     * @param courseID
     * @return
     */
    List<Section> sections(Integer courseID);

    /**
     * 根据课程Id获取课程章节及分页
     * @param query
     * @return
     */
    List<Section> secList(Map query);

    /**
     * 获取章节总数量
     * @param courseID
     * @return
     */
    Integer couSec(Integer courseID);

    /**
     * 根据课程Id获取章节最大顺序号
     * @param courseID
     * @return
     */
    Integer maxSeq(Integer courseID);

    /**
     * 添加章节
     * @param section
     * @return
     */
    Integer secAdd(Map section);

    /**
     * 根据章节Id删除章节
     * @param sectionID
     * @return
     */
    Integer secDel(Integer sectionID);

    /**
     * 根据章节Id修改章节
     * @param sectionID
     * @param field
     * @param value
     * @return
     */
    Integer secUpd(String sectionID,String field,String value);

    /**
     * 根据课程ID修改顺序号
     * @param sectionID
     * @param sectionSeq
     * @return
     */
    Integer secUpSeq(Integer sectionID,Integer sectionSeq);

    /**
     * 根据课程ID及章节顺序号获取章节
     * @param sectionCid
     * @param sectionSeq
     * @return
     */
    Section section(Integer sectionCid,Integer sectionSeq);
}
