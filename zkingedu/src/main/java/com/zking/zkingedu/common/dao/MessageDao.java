package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Message;

import java.util.List;

/**
 * 消息接口
 */
public interface MessageDao {

    /**
     * 增加一条消息
     * @param message
     * @return
     */
    Integer insertMessage(Message message);

    /**
     * 查询个人所有消息
     * @return
     */
    List<Message> getMyMessages(Integer messageUid);

    /**
     * 用户未读消息改已读
     * @param messageID 消息ID
     * @return
     */
    int editMessageState(Integer messageID);

    /**
     * 根据得到帖子ID
     * @param messageID 消息ID
     * @return Message
     */
    Message getMessageByMessageID(Integer messageID);
}
