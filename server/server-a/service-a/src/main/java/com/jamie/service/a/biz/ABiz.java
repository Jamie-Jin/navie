package com.jamie.service.a.biz;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.jamie.api.a.entity.AEntity;
import com.jamie.api.a.entity.AProducerLogEntity;
import com.jamie.api.a.vo.AVo;
import com.jamie.api.b.api.Bapi;
import com.jamie.service.a.dao.ADao;
import com.jamie.service.a.dao.AProducerLogDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ABiz {

    @Autowired
    private ADao aDao;

    @Autowired
    private AProducerLogDao aProducerLogDao;

    // 被调用方要加上DTXPropagation.SUPPORTS，为什么要加，未知
    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional(rollbackFor = Exception.class)
    public int insertA(AVo aVo){
        AEntity aEntity = new AEntity();
        BeanUtils.copyProperties(aVo, aEntity);
        return aDao.insertA(aEntity);
    }

    // 消息生产者插入生产日志
    public int insertAProducerLog(AProducerLogEntity entity){
        return aProducerLogDao.insertAProducerLog(entity);
    }

    public String getData(){
        return JSON.toJSONString(aDao.getData());
    }

}
