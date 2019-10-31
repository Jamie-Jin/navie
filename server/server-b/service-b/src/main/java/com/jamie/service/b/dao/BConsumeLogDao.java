package com.jamie.service.b.dao;

import com.jamie.api.b.entity.BConsumerLogEntity;
import com.jamie.api.message.vo.NotifyVo;
import com.jamie.common_dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;

@Repository
public class BConsumeLogDao extends BaseDao<BConsumerLogEntity> {
    // 创建消费日志
    public int insertBConsumeLog(BConsumerLogEntity entity){
        return singleInsert(entity);
    }

    // 消息消费成功
    public int consumeSuccess(BConsumerLogEntity entity){
        HashMap<String, Object> param = new HashMap<>();
        param.put("correlationId", entity.getCorrelationId());
        param.put("updateTime", new Date());
        return getSqlSessionTemplate().update(getStatement("consumeSuccess"), param);
    }

    // 消息消费失败
    public int consumeFail(BConsumerLogEntity entity){
        HashMap<String, Object> param = new HashMap<>();
        param.put("correlationId", entity.getCorrelationId());
        param.put("updateTime", new Date());
        return getSqlSessionTemplate().update(getStatement("consumeFail"), param);
    }

    // 检查消息是否已成功消费，保证消息不会重复消费
    public int checkConsume(NotifyVo notifyVo){
        HashMap<String, Object> param = new HashMap<>();
        param.put("correlationId", notifyVo.getCorrelationId());

        return getSqlSessionTemplate().selectOne(getStatement("checkConsume"), param);
    }

}
