package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.System;

import java.util.List;
import java.util.Map;

/**
 * 课程体系接口
 */
public interface SystemService {
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
    Integer upSysimg(String systemID,String systemImg);

    /**
     * 根据体系Id修改体系
     * @param system
     * @return
     */
    Integer upSys(System system);

    /**
     * 添加体系
     * @param system
     * @return
     */
    Integer systemAdd(System system);

    /**
     * 获取所有体系Map
     * @return
     */
    List<Map> sysMap(Integer systemFid);
}
