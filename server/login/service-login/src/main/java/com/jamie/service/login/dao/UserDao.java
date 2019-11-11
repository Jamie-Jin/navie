package com.jamie.service.login.dao;

import com.jamie.api.login.entity.UserEntity;
import com.jamie.api.login.vo.UserVo;
import com.jamie.common_dao.BaseDao;
import com.jamie.common_util.redis.RedisConstant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDao extends BaseDao<UserEntity> {

    public Integer insertUser(UserEntity userEntity){
        return singleInsert(userEntity);
    }

    // 根据条件查询用户信息
    @Cacheable(value = RedisConstant.user, key = "#userVo.getAccount()")
    public UserEntity getUserBy(UserVo userVo){
        HashMap<String, Object> param = new HashMap<>();
        param.put("account", userVo.getAccount());
        return getSqlSessionTemplate().selectOne(getStatement("getUserBy"), param);
    }

}
