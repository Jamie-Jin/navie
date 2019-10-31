package com.jamie.service.a.biz;

import com.jamie.api.a.entity.AEntity;
import com.jamie.api.a.entity.AProducerLogEntity;
import com.jamie.api.a.vo.AVo;
import com.jamie.service.a.dao.ADao;
import com.jamie.service.a.dao.AProducerLogDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ABiz {

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

}
