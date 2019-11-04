package com.jamie.service.a.rest;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.jamie.api.a.api.Aapi;
import com.jamie.api.a.entity.AProducerLogEntity;
import com.jamie.api.a.urls.Urls;
import com.jamie.api.a.vo.AVo;
import com.jamie.api.b.api.Bapi;
import com.jamie.api.b.entity.BEntity;
import com.jamie.api.message.api.MessageApi;
import com.jamie.api.message.vo.NotifyVo;
import com.jamie.service.a.biz.ABiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ARest implements Aapi {

    @Autowired
    private ABiz aBiz;

    @Autowired
    private MessageApi messageApi;

    @Autowired
    private Bapi bapi;

    @Override
    @PostMapping(Urls.insertA)
    public int insertA(@RequestBody AVo aVo) {
        return aBiz.insertA(aVo);
    }

    @Override
    @PostMapping(Urls.messageAToB)
    public void messageAToB(AVo aVo) {
        try {
            NotifyVo notifyVo = new NotifyVo();
            notifyVo.setRoutingKey("com.jamie.hello");   //消息路由键
            notifyVo.setCorrelationId(UUID.randomUUID().toString()); //消息唯一标识
            notifyVo.setMessage(aVo);
            notifyVo.setBody(JSON.toJSONString(aVo));

            // 消息生产者插入生产日志
            AProducerLogEntity entity = new AProducerLogEntity();
            entity.setCorrelationId(notifyVo.getCorrelationId());
            entity.setRoutingKey(notifyVo.getRoutingKey());
            entity.setBody(JSON.toJSONString(notifyVo.getMessage()));
            aBiz.insertAProducerLog(entity);

            // 发送消息
            messageApi.send(notifyVo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getData() {
        return aBiz.getData();
    }


    // 在模块A和模块B分别插入数据，测试TX-LCN分布式事务是否生效
    @LcnTransaction
    @PostMapping(Urls.insertAandB)
    @Override
    public int insertAandB(String msg) {
        AVo aVo = new AVo();
        aVo.setMsg(msg);
        int aRes = aBiz.insertA(aVo);

        BEntity bEntity = new BEntity();
        bEntity.setMsg(msg);
        int bRes = bapi.insertB(bEntity);

        return aRes + bRes;
    }

}
