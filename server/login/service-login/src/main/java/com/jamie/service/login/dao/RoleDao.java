package com.jamie.service.login.dao;

import com.jamie.common_dao.BaseDao;
import com.jamie.service.login.entity.RoleEntity;
import com.jamie.service.login.vo.RoleVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class RoleDao extends BaseDao<RoleEntity> {

    public int insertRole(RoleEntity entity){
        return singleInsert(entity);
    }

    public RoleEntity getRole(RoleVo roleVo){
        HashMap<String,Object> param = new HashMap<>();
        param.put("role", roleVo.getRole());
        return getSqlSessionTemplate().selectOne(getStatement("getRole"), param);
    }

}
