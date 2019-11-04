package com.jamie.service.login.dao;

import com.jamie.common_dao.BaseDao;
import com.jamie.service.login.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<UserEntity> {

    public Integer insertUser(UserEntity userEntity){
        return singleInsert(userEntity);
    }

}
