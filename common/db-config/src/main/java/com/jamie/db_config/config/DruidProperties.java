package com.jamie.db_config.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Getter
public class DruidProperties {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.druid.validation-query-timeout}")
    private Integer validationQueryTimeout;

    @Value("${spring.datasource.druid.test-on-borrow}")
    private Boolean testOnBorrow;

    @Value("${spring.datasource.druid.test-while-idle}")
    private Boolean testWhileIdle;

    @Value("${spring.datasource.druid.test-on-return}")
    private Boolean testOnReturn;

    @Value("${spring.datasource.druid.test-on-return}")
    private Boolean poolPreparedStatements;

}
