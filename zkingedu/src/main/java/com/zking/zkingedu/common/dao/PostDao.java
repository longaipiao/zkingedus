package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 帖子接口
 */
public interface PostDao {

    /**
     * 查询所有帖子
     * @return
     */
    List<Map<String,Object>> queryAllPost();

    /**
     * 根据用户id查询该用户所发过的帖子
     * @param uid
     * @return
     */
    List<Map<String,Object>> queryPostByUid(Integer uid);

    /**
     * 根据用户id查询该用户所发过的帖子(分页)
     * @param uid
     * @return
     */
    List<Map<String,Object>> queryPostByUidPage(@Param("uid")Integer uid,@Param("size") Integer size,@Param("start") Integer start);

    /**
     * 根据类别查询所有帖子
     * @return
     */
    List<Map<String,Object>> queryAllPostByType(String type);

    /**
     * 根据类别查询所有帖子
     * @return
     */
    List<Map<String,Object>> queryAllPostByTypeTo(@Param("type") String type,@Param("value") String value);
    /**
     * 查询所有帖子page
     * @return
     */
    List<Map<String,Object>> queryPagePost(@Param("page") Integer page,@Param("size") Integer size);

    /**
     * 按类别查询所有帖子page
     * @return
     */
    List<Map<String,Object>> queryPagePostByType(@Param("page") Integer page,@Param("size") Integer size,@Param("type") String type);

    /**
     * 根据id查询帖子
     * @return
     */
    List<Map<String,Object>> queryPostByid(@Param("post") Post post);

    /**
     * 根据用户id查询收藏的帖子
     * @return
     */
    List<Map<String,Object>> queryPostByUserId(@Param("uid")Integer uid);

    /**
     * 根据用户id查询收藏的帖子Page
     * @return
     */
    List<Map<String,Object>> queryPostByUserIdPage(@Param("uid")Integer uid,@Param("size") Integer size,@Param("page") Integer page);

    /**
     * 发表帖子方法
     * @param post
     * @return
     */
    int addPost(@Param("post") Post post);

    /**
     * 封禁OR解封帖子
     * @param post_id
     * @return
     */
    int banPost(@Param("post_id") Integer post_id,@Param("type") Integer type);

    /**
     * 增加访问量
     * @param post_id
     * @return
     */
    int pageView(Integer post_id);


}
