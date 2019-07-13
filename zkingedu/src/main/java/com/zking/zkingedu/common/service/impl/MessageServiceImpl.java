package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.MessageDao;
import com.zking.zkingedu.common.model.Message;
import com.zking.zkingedu.common.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息接口   实现层
 */
@Service("messageSevice")
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageDao messageDao;

    /**
     * 增加一条消息
     *
     * @param message
     * @return
     */
    @Override
    public Integer insertMessage(Message message) {
        return messageDao.insertMessage(message);
    }

    /**
     * 查询个人所有消息
     *
     * @return
     */
    @Override
    public List<Message> getMyMessages(Integer messageUid) {
        return messageDao.getMyMessages(messageUid);
    }

    /**
     * 用户未读消息改已读
     *
     * @param messageID
     * @return
     */
    @Override
    public int editMessageState(Integer messageID) {
        return messageDao.editMessageState(messageID);
    }

    /**
     * 根据得到帖子ID
     *
     * @param messageID 消息ID
     * @return Message
     */
    @Override
    public Message getMessageByMessageID(Integer messageID) {
        return messageDao.getMessageByMessageID(messageID);
    }
}
