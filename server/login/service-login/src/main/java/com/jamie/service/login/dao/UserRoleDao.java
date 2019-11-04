package com.jamie.service.login.dao;

import com.jamie.common_dao.BaseDao;
import com.jamie.service.login.entity.UserRoleEntity;
import com.jamie.service.login.vo.UserRoleVo;
import com.jamie.service.login.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRoleDao extends BaseDao<UserRoleEntity> {

    public int insertUserRole(UserRoleEntity entity){
        return singleInsert(entity);
    }

    // 获取用户以及角色信息
    public UserRoleVo getUserRole(UserVo userVo){
        HashMap<String, Object> param = new HashMap<>();
        param.put("account", userVo.getAccount());
        return getSqlSessionTemplate().selectOne(getStatement("getUserRole"), param);
    }
}
