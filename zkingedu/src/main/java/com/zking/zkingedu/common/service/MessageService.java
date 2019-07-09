package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Message;

import java.util.List;

/**
 * 消息接口
 */

public interface MessageService {

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
}
