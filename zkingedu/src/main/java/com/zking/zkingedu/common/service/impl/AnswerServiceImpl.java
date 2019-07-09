package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.AnswerDao;
import com.zking.zkingedu.common.dao.TitleDao;
import com.zking.zkingedu.common.model.Answer;
import com.zking.zkingedu.common.model.Title;
import com.zking.zkingedu.common.service.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 答案接口服务
 */
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {
    @Resource
    private AnswerDao answerDao;

    @Override
    public List<Answer> getAnswerAll(Integer tid) {
        return answerDao.getAnswerAll(tid);
    }

    @Override
    public Integer addAnswer(List<Answer> ls) {
        return answerDao.addAnswer(ls);
    }

    @Override
    public Integer deletedaan(Integer tid) {
        return answerDao.deletedaan(tid);
    }

    @Override
    public Integer uppdatedaana(Answer answer) {
        return answerDao.uppdatedaana(answer);
    }

    @Override
    public Integer uppdatedaanb(Answer answer) {
        return answerDao.uppdatedaand(answer);
    }

    @Override
    public Integer uppdatedaanc(Answer answer) {
        return answerDao.uppdatedaanc(answer);
    }

    @Override
    public Integer uppdatedaand(Answer answer) {
        return answerDao.uppdatedaand(answer);
    }

    @Override
    public List<Title> getAll(Title title) {
        return answerDao.getAll(title);
    }

    @Override
    public Integer addTitle(Title title) {
        return answerDao.addTitle(title);
    }

    @Override
    public Integer updatekq(Integer tid) {
        return answerDao.updatekq(tid);
    }

    @Override
    public Integer updategb(Integer tid) {
        return answerDao.updategb(tid);
    }

    @Override
    public Integer deletetim(Integer tid) {
        return answerDao.deletetim(tid);
    }

    @Override
    public List<Title> getAlls(Integer tid) {
        return answerDao.getAlls(tid);
    }

    @Override
    public Integer updatetim(Title title) {
        return answerDao.updatetim(title);
    }




}
