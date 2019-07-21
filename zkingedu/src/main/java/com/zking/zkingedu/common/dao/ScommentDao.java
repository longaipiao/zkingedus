package com.zking.zkingedu.common.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程评论接口
 */
@Mapper
public interface ScommentDao {
    /**
     * 根据课程ID获取课程评论
     * @param page
     * @param limit
     * @param courseID
     * @return
     */
    List<Map> coms(@Param("page") Integer page,@Param("limit") Integer limit,@Param("courseID") Integer courseID);

    /**
     * 添加课程评论
     * @param courseID
     * @param userID
     * @param ccontent
     * @return
     */
    Integer comAdd(@Param("courseID") Integer courseID,@Param("userID") Integer userID,@Param("ccontent") String ccontent);

    /**
     * 评论数
     * @param courseID
     * @return
     */
    Integer coucom(Integer courseID);
}
