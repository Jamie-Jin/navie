package com.jamie.service.message.rest;

import com.jamie.api.message.api.MessageApi;
import com.jamie.api.message.urls.Urls;
import com.jamie.api.message.vo.NotifyVo;
import com.jamie.service.message.biz.MessageBiz;
import com.jamie.service.message.biz.MessageBiz2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRest implements MessageApi {
    @Autowired
    private MessageBiz messageBiz;

    @Autowired
    private MessageBiz2 messageBiz2;

    /**
     * 发送消息
     * @param notifyVo
     * @throws Exception
     */
    @Override
    @PostMapping(Urls.sendMessage)
    public void send(@RequestBody NotifyVo notifyVo) throws Exception {
        messageBiz2.send(notifyVo);
    }

    /**
     * 发消息前记录消息日志
     * @param notifyVo
     * @return
     */
    @Override
    @PostMapping(Urls.insertMessaeLog)
    public int insertMessageLog(@RequestBody NotifyVo notifyVo) {
        return messageBiz2.insertMessageLog(notifyVo);
    }
}
