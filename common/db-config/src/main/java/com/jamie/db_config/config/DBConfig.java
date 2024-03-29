package com.jamie.db_config.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Autowired
    private DruidProperties druid;

    @Bean
    @Primary
    @RefreshScope
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(druid.getUrl());
        dataSource.setUsername(druid.getUserName());
        dataSource.setPassword(druid.getPassword());
        dataSource.setDriverClassName(druid.getDriverClassName());

        dataSource.setValidationQuery(druid.getValidationQuery());
        dataSource.setValidationQueryTimeout(druid.getValidationQueryTimeout());
        dataSource.setTestOnBorrow(druid.getTestOnBorrow());
        dataSource.setTestWhileIdle(druid.getTestWhileIdle());
        dataSource.setTestOnReturn(druid.getTestOnReturn());
        dataSource.setPoolPreparedStatements(druid.getPoolPreparedStatements());

        return dataSource;
    }

}
