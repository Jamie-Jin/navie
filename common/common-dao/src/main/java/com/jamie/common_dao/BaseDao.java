package com.jamie.common_dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao<T> extends SqlSessionDaoSupport {
    private static final String singleInsert = "insert";

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public String getStatement(String sqlId){
        String path = this.getClass().getName();
        return path + "." + sqlId;
    }

    // 插入单条记录
    public int singleInsert(T entity){
        return getSqlSessionTemplate().insert(getStatement(singleInsert), entity);
    }

}
