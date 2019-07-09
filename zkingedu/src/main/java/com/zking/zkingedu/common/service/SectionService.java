package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Section;

import java.util.List;

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
}
