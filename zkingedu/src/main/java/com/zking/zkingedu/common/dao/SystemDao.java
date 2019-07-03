package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.System;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取所有体系（后台）
     * @param query
     * @return
     */
    List<System> sysList(Map query);

    /**
     * 获取所有体系数量（后台）
     * @param query
     * @return
     */
    Integer syscount(Map query);

    /**
     * 根据体系Id修改体系图片路径
     * @param systemID
     * @param systemImg
     * @return
     */
    Integer upSysimg(@Param("systemID") String systemID, @Param("systemImg") String systemImg);

    /**
     * 根据体系Id修改体系
     * @param system
     * @return
     */
    Integer upSys(System system);
}
