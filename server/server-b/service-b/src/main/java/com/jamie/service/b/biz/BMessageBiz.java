package com.jamie.service.b.biz;

import com.jamie.api.b.entity.BConsumerLogEntity;
import com.jamie.api.message.vo.NotifyVo;
import com.jamie.service.b.dao.BConsumeLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BMessageBiz {

    @Autowired
    private BConsumeLogDao bConsumeLogDao;

    // 插入消息消费日志
    public int insertConsumeLog(NotifyVo notifyVo){
        BConsumerLogEntity entity = new BConsumerLogEntity();
        // 消息唯一标识
        entity.setCorrelationId(notifyVo.getCorrelationId());
        // 消息路由键
        entity.setRoutingKey(notifyVo.getRoutingKey());
        // 消息体（JSON）
        entity.setBody(notifyVo.getBody());

        return bConsumeLogDao.insertBConsumeLog(entity);
    }

    // 消息消费成功
    public int consumeSuccess(NotifyVo notifyVo){
        BConsumerLogEntity entity = new BConsumerLogEntity();
        entity.setCorrelationId(notifyVo.getCorrelationId());
        entity.setUpdateTime(new Date());
        return bConsumeLogDao.consumeSuccess(entity);
    }

    // 消息消费失败
    public int consumeFail(NotifyVo notifyVo){
        BConsumerLogEntity entity = new BConsumerLogEntity();
        entity.setCorrelationId(notifyVo.getCorrelationId());
        entity.setUpdateTime(new Date());
        return bConsumeLogDao.consumeFail(entity);
    }

    // 检查消息是否已成功消费，保证消息不会重复消费
    public int checkConsume(NotifyVo notifyVo){
        return bConsumeLogDao.checkConsume(notifyVo);
    }

}
