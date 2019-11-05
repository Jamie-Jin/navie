package com.jamie.service.login.dao;

import com.jamie.common_dao.BaseDao;
import com.jamie.service.login.entity.RoleMenuEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RoleMenuDao extends BaseDao<RoleMenuEntity> {

    public int insertRoleMenu(RoleMenuEntity entity){
        return singleInsert(entity);
    }

}
