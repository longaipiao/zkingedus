package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Advertising;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 广告接口
 */
public interface AdvertisingService {
    /**
     * 获取广告列表
     * @return
     */
    List<Advertising> getAllAdvertising();

    /**
     * 添加广告
     * @param advertising
     * @return
     */
    int addAdvertising(Advertising advertising);

    /**
     * 修改上下架状态
     * @param aid type
     * @return
     */
    int updateAdvertising(@Param("aid") Integer aid, @Param("type") Integer type);

    /**
     * 查询已启用的广告数量
     * @return
     */
    long queryAdvertisingSum();
}
