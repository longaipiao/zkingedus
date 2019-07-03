package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Post;
import javafx.geometry.Pos;
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
     * 根据id查询帖子
     * @return
     */
    List<Map<String,Object>> queryPostByid(@Param("post") Post post);

    /**
     * 发表帖子方法
     * @param post
     * @return
     */
    int addPost(@Param("post") Post post);
}
