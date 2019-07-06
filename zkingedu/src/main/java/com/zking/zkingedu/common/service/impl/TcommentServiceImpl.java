package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.TcommentDao;
import com.zking.zkingedu.common.model.Give;
import com.zking.zkingedu.common.model.Tcomment;
import com.zking.zkingedu.common.service.TcommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 帖子评论接口   服务实现层
 */
@Service("tcommentService")
public class TcommentServiceImpl implements TcommentService {
    @Autowired
    private TcommentDao tcommentDao;

    @Override
    public int addComment(Tcomment tcomment) {
        return tcommentDao.addComment(tcomment);
    }

    @Override
    public List<Map<String, Object>> queryTcomment(Integer pid) {
        return tcommentDao.queryTcomment(pid);
    }

    @Override
    public int addCollection(Integer post_id, Integer user_id, Integer type_id, String cTime) {
        return tcommentDao.addCollection(post_id, user_id, type_id, cTime);
    }

    @Override
    public int deleteConllection(Integer post_id, Integer user_id) {
        return tcommentDao.deleteConllection(post_id, user_id);
    }

    @Override
    public Map<String, Object> queryCollection(Integer post_id, Integer user_id) {
        return tcommentDao.queryCollection(post_id, user_id);
    }

    @Override
    public int addGive(Integer give_pid, Integer give_uid, String give_time) {
        return tcommentDao.addGive(give_pid, give_uid, give_time);
    }

    @Override
    public int delGive(Integer give_pid, Integer give_uid) {
        return tcommentDao.delGive(give_pid, give_uid);
    }

    @Override
    public Give queryGiveById(Integer give_pid, Integer give_uid) {
        return tcommentDao.queryGiveById(give_pid, give_uid);
    }

    @Override
    public int queryCountPost(Integer post_id) {
        return tcommentDao.queryCountPost(post_id);
    }

    @Override
    public int queryCountGive(Integer give_pid) {
        return tcommentDao.queryCountGive(give_pid);
    }
}
