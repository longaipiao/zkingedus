package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.PostDao;
import com.zking.zkingedu.common.model.Post;
import com.zking.zkingedu.common.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 帖子接口  实现层
 */
@Service("postService")
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
    @Override
    public List<Map<String,Object>> queryAllPost() {
        return postDao.queryAllPost();
    }

    @Override
    public List<Map<String, Object>> queryPostByid(Post post) {
        return postDao.queryPostByid(post);
    }

    @Override
    public int addPost(Post post) {
        return postDao.addPost(post);
    }
}
