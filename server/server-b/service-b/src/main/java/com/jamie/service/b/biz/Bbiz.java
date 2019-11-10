package com.jamie.service.b.biz;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.jamie.api.b.entity.BEntity;
import com.jamie.service.b.dao.BDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bbiz {

    @Autowired
    private BDao bDao;

    // 被调用方要加上DTXPropagation.SUPPORTS，为什么要加，未知
    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    public int insertB(BEntity bEntity){
        //bDao.insertB(bEntity);
        //throw new RuntimeException("测试分布式事务回滚");

        return bDao.insertB(bEntity);
    }

    // 获取最新的数据
    public BEntity getLatestData(){
        return bDao.getLatestData();
    }

}
