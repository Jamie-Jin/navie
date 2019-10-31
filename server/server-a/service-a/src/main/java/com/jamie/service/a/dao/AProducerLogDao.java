package com.jamie.service.a.dao;

import com.jamie.api.a.entity.AProducerLogEntity;
import com.jamie.common_dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class AProducerLogDao extends BaseDao<AProducerLogEntity> {

    public int insertAProducerLog(AProducerLogEntity entity){
        return singleInsert(entity);
    }

}
