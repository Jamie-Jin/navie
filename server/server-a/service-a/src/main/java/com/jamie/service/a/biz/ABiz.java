package com.jamie.service.a.biz;

import com.alibaba.fastjson.JSON;
import com.jamie.api.a.api.Aapi;
import com.jamie.api.a.entity.AEntity;
import com.jamie.api.a.entity.AProducerLogEntity;
import com.jamie.api.a.vo.AVo;
import com.jamie.api.b.api.Bapi;
import com.jamie.api.b.entity.BEntity;
import com.jamie.service.a.dao.ADao;
import com.jamie.service.a.dao.AProducerLogDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ABiz {

    @Autowired
    private Bapi bapi;

    @Autowired
    private ADao aDao;

    @Autowired
    private AProducerLogDao aProducerLogDao;

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

    // 在模块A和模块B分别插入数据，测试TX-LCN分布式事务是否生效
    public int insertAandB(String msg){
        AEntity aEntity = new AEntity();
        aEntity.setMsg(msg);
        int aRes = aDao.insertA(aEntity);

        BEntity bEntity = new BEntity();
        bEntity.setMsg(msg);
        int bRes = bapi.insertB(bEntity);

        return aRes + bRes;
    }

}
