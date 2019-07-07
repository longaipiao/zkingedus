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
     * 根据类别查询所有帖子
     * @return
     */
    List<Map<String,Object>> queryAllPostByType(String type);

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
     * 发表帖子方法
     * @param post
     * @return
     */
    int addPost(@Param("post") Post post);




}
