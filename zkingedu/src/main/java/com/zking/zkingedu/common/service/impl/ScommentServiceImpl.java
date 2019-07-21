package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.ScommentDao;
import com.zking.zkingedu.common.service.ScommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 课程评论接口   实现
 */
@Service("scommentService")
public class ScommentServiceImpl implements ScommentService {
    @Resource
    private ScommentDao scommentDao;

    /**
     * 根据课程ID获取课程评论
     * @param page
     * @param limit
     * @param courseID
     * @return
     */
    @Override
    public List<Map> coms(Integer page, Integer limit, Integer courseID) {
        return scommentDao.coms(page,limit,courseID);
    }

    /**
     * 添加课程评论
     * @param courseID
     * @param userID
     * @param ccontent
     * @return
     */
    @Override
    public Integer comAdd(Integer courseID, Integer userID, String ccontent) {
        return scommentDao.comAdd(courseID,userID,ccontent);
    }

    /**
     * 评论数
     * @param courseID
     * @return
     */
    @Override
    public Integer coucom(Integer courseID) {
        return scommentDao.coucom(courseID);
    }

}
