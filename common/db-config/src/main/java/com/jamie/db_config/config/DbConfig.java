package com.jamie.db_config.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 通过配置中心热部署数据源
 */
@Configuration
public class DbConfig {
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
        dataSource.setDriverClassName(druid.getDriverName());
        return dataSource;
    }

}
