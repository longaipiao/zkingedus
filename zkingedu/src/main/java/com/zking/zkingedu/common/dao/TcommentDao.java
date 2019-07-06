package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Give;
import com.zking.zkingedu.common.model.Tcomment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 帖子评论接口
 */
@Mapper
public interface TcommentDao {
    /**
     * 添加评论
     * @param tcomment
     * @return
     */
    int addComment(@Param("tcomment") Tcomment tcomment);

    /**
     * 根据帖子id查询该帖子下的所有评论
     * @param pid
     * @return
     */
    List<Map<String,Object>> queryTcomment(Integer pid);

    /**
     * 添加收藏
     * @param post_id
     * @param user_id
     * @param type_id
     * @param cTime
     * @return
     */
    int addCollection(@Param("post_id") Integer post_id,@Param("user_id") Integer user_id,@Param("type_id") Integer type_id,@Param("cTime") String cTime);

    /**
     * 取消收藏
     * @param post_id
     * @param user_id
     * @return
     */
    int deleteConllection(@Param("post_id") Integer post_id,@Param("user_id") Integer user_id);

    /**
     * 根据用户id和帖子id查询该用户是否收藏了该帖子
     * @param post_id
     * @param user_id
     * @return
     */
    Map<String,Object> queryCollection(@Param("post_id") Integer post_id,@Param("user_id") Integer user_id);


    /**
     * 点赞
     * @param give_pid
     * @param give_uid
     * @param give_time
     * @return
     */
    int addGive(@Param("give_pid") Integer give_pid,@Param("give_uid") Integer give_uid,@Param("give_time") String give_time);

    /**
     * 取消点赞
     * @param give_pid
     * @param give_uid
     * @return
     */
    int delGive(@Param("give_pid") Integer give_pid,@Param("give_uid") Integer give_uid);

    /**
     * 查询该用户是否给此帖子点过赞
     * @param give_pid
     * @param give_uid
     * @return
     */
    Give queryGiveById(@Param("give_pid") Integer give_pid,@Param("give_uid") Integer give_uid);

    /**
     * 查询该帖子的总收藏数
     * @param post_id
     * @return
     */
    int queryCountPost(Integer post_id);
    /**
     * 查询该帖子的总点赞数
     * @param give_pid
     * @return
     */
    int queryCountGive(Integer give_pid);

}
