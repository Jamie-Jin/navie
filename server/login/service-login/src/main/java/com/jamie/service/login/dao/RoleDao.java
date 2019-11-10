package com.jamie.service.login.dao;

import com.jamie.common_dao.BaseDao;
import com.jamie.service.login.entity.RoleEntity;
import com.jamie.service.login.vo.RoleVo;
import com.jamie.service.login.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

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

    // 获取用户对应的角色
    public List<RoleEntity> getRolesByUser(UserVo userVo){
        HashMap<String,Object> param = new HashMap<>();
        param.put("account", userVo.getAccount());
        return getSqlSessionTemplate().selectList(getStatement("getRolesByUser"), param);
    }

}
