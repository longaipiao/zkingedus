package com.zking.zkingedu.common.service;

import java.util.List;
import java.util.Map;

/**
 * 课程评论接口
 */
public interface ScommentService {
    /**
     * 根据课程ID获取课程评论
     * @param page
     * @param limit
     * @param courseID
     * @return
     */
    List<Map> coms(Integer page,Integer limit,Integer courseID);

    /**
     * 添加课程评论
     * @param courseID
     * @param userID
     * @param ccontent
     * @return
     */
    Integer comAdd(Integer courseID,Integer userID,String ccontent);

    /**
     * 评论数
     * @param courseID
     * @return
     */
    Integer coucom(Integer courseID);
}
