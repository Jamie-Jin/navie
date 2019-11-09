package com.jamie.service.a.rest;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.jamie.api.a.api.Aapi;
import com.jamie.api.a.entity.AEntity;
import com.jamie.api.a.entity.AProducerLogEntity;
import com.jamie.api.a.urls.Urls;
import com.jamie.api.a.vo.AVo;
import com.jamie.api.b.api.Bapi;
import com.jamie.api.b.entity.BEntity;
import com.jamie.api.c.api.CApi;
import com.jamie.api.c.vo.CVo;
import com.jamie.api.message.api.MessageApi;
import com.jamie.api.message.vo.NotifyVo;
import com.jamie.service.a.biz.ABiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ARest implements Aapi {
    private static final Logger logger = LoggerFactory.getLogger(ARest.class);

    @Autowired
    private ABiz aBiz;

    @Autowired
    private MessageApi messageApi;

    @Autowired
    private Bapi bapi;

    @Autowired
    private CApi cApi;

    @Override
    @PostMapping(Urls.insertA)
    public int insertA(@RequestBody AVo aVo) {
        return aBiz.insertA(aVo);
    }

    // 向模块B发送消息
    @Override
    @PostMapping(Urls.sendMsgToB)
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

    // 获取最新的数据
    @PostMapping(Urls.getLatestData)
    @Override
    public String getLatestData() {
        return aBiz.getLatestData();
    }

    // 在模块A和模块B分别插入数据，测试TX-LCN分布式事务是否生效
    @LcnTransaction
    @PostMapping(Urls.insertAB)
    @Override
    public int insertAB(String msg) {
        AVo aVo = new AVo();
        aVo.setMsg(msg);
        int aRes = aBiz.insertA(aVo);

        BEntity bEntity = new BEntity();
        bEntity.setMsg(msg);
        int bRes = bapi.insertB(bEntity);

        return aRes + bRes;
    }

    // 按照网上说法，调用方的@LcnTransaction是不需要加参数的
    @LcnTransaction
    @PostMapping(Urls.insertABC)
    @Override
    public int insertABC(String msg) {
        AVo aVo = new AVo();
        aVo.setMsg(msg);
        int aRes = aBiz.insertA(aVo);

        BEntity bEntity = new BEntity();
        bEntity.setMsg(msg);
        int bRes = bapi.insertB(bEntity);

        CVo cVo = new CVo();
        cVo.setVal(msg);
        cVo.setKey(msg);
        cVo.setExpireTime(60);
        cApi.insertC(cVo);

        return aRes + bRes;
    }

    // 在同一个事务中，先在A插入一条数据，然后查询这条数据，然后B得到这条数据后也将插入数据库中
    @LcnTransaction
    @PostMapping(Urls.insertAFirstThenB)
    @Override
    public int insertAFirstThenB(@RequestBody String msg) {
        // 在A中插入一条数据
        AVo aVo = new AVo();
        aVo.setMsg(msg);
        int aResult = aBiz.insertA(aVo);

        logger.info("数据模块A插入的数据：" + JSON.toJSONString(aVo));

        // 立马取出刚插入的数据，以此数据插入B
        AEntity aEntity = aBiz.getDataBy(aVo);

        logger.info("从数据模块A获取到的数据：" + JSON.toJSONString(aEntity));

        int bResult = 0;
        if (aEntity != null){
            BEntity bEntity = new BEntity();
            bEntity.setMsg(aEntity.getMsg());
            bResult = bapi.insertB(bEntity);
        }

        return aResult + bResult;
    }

}
