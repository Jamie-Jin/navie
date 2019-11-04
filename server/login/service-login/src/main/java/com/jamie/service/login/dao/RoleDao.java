package com.jamie.service.login.dao;

import com.jamie.common_dao.BaseDao;
import com.jamie.service.login.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends BaseDao<RoleEntity> {

    public int insertRole(RoleEntity entity){
        return singleInsert(entity);
    }

}
