package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Section;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 章节接口
 */
@Mapper
public interface SectionDao {
    /**
     * 根据课程Id获取课程章节
     * @param courseID
     * @return
     */
    List<Section> sections(Integer courseID);

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
}
