package com.jamie.service.a.dao;

import com.jamie.api.a.entity.AEntity;
import com.jamie.common_dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class ADao extends BaseDao<AEntity> {

    public int insertA(AEntity aEntity){
        return singleInsert(aEntity);
    }

}
