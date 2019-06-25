package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.System;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程体系接口
 */
@Mapper
public interface SystemDao {
    /**
     * 获取所有体系
     * @return
     */
    List<System> systems(Integer systemFid);

    /**
     * 根据体系Id查询体系
     * @param systemID
     * @return
     */
    System system(Integer systemID);
}
