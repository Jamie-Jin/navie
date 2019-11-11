package com.jamie.service.a.biz;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.jamie.api.a.entity.AEntity;
import com.jamie.api.a.entity.AProducerLogEntity;
import com.jamie.api.a.vo.AVo;
import com.jamie.api.b.api.Bapi;
import com.jamie.service.a.dao.ADao;
import com.jamie.service.a.dao.AProducerLogDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ABiz {

    @Autowired
    private ADao aDao;

    @Autowired
    private AProducerLogDao aProducerLogDao;

    // 被调用方要加上DTXPropagation.SUPPORTS，为什么要加，未知
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    public int insertA(AVo aVo){
        AEntity aEntity = new AEntity();
        BeanUtils.copyProperties(aVo, aEntity);
        return aDao.insertA(aEntity);
    }

    // 消息生产者插入生产日志
    public int insertAProducerLog(AProducerLogEntity entity){
        return aProducerLogDao.insertAProducerLog(entity);
    }

    // 获取最新的数据
    public String getLatestData(){
        return JSON.toJSONString(aDao.getLatestData());
    }

    // 根据查询条件获取数据模块A数据
    public AEntity getDataBy(AVo aVo){
        return aDao.getDataBy(aVo);
    }

    // 根据条件更新
    public int updateA(AVo aVo){
        AEntity aEntity = new AEntity();
        BeanUtils.copyProperties(aVo, aEntity);
        aEntity.setUpdateTime(new Date());

        return aDao.updateA(aEntity);
    }

}
