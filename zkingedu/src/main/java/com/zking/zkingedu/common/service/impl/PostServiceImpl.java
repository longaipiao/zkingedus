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
    public List<Map<String, Object>> queryAllPostByType(String type) {
        return postDao.queryAllPostByType(type);
    }

    @Override
    public List<Map<String, Object>> queryAllPostByTypeTo(String type, String value) {
        return postDao.queryAllPostByTypeTo(type, value);
    }

    @Override
    public List<Map<String, Object>> queryPagePost(Integer page, Integer size) {
        return postDao.queryPagePost(page, size);
    }

    @Override
    public List<Map<String, Object>> queryPagePostByType(Integer page, Integer size, String type) {
        return postDao.queryPagePostByType(page, size, type);
    }

    @Override
    public List<Map<String, Object>> queryPostByid(Post post) {
        return postDao.queryPostByid(post);
    }

    @Override
    public List<Map<String, Object>> queryPostByUserId(Integer uid) {
        return postDao.queryPostByUserId(uid);
    }

    @Override
    public List<Map<String, Object>> queryPostByUserIdPage(Integer uid, Integer size, Integer page) {
        return postDao.queryPostByUserIdPage(uid, size, page);
    }

    @Override
    public int addPost(Post post) {
        return postDao.addPost(post);
    }

    @Override
    public int banPost(Integer post_id, Integer type) {
        return postDao.banPost(post_id,type);
    }

    @Override
    public int pageView(Integer post_id) {
        return postDao.pageView(post_id);
    }

    @Override
    public List<Map<String, Object>> queryPostByUid(Integer uid) {
        return postDao.queryPostByUid(uid);
    }

    @Override
    public List<Map<String, Object>> queryPostByUidPage(Integer uid, Integer size, Integer start) {
        return postDao.queryPostByUidPage(uid, size, start);
    }
}
