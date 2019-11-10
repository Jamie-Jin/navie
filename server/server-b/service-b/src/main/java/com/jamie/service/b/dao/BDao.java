package com.jamie.service.b.dao;

import com.jamie.api.b.entity.BEntity;
import com.jamie.common_dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class BDao extends BaseDao<BEntity> {

    public int insertB(BEntity bEntity){
        return singleInsert(bEntity);
    }

    public BEntity getLatestData(){
        return getSqlSessionTemplate().selectOne(getStatement("getLatestData"));
    }
}
