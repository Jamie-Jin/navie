package com.jamie.service.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableDiscoveryClient  //注册中心客户端（微服务注册到注册中心）
@EnableZuulProxy        //启用Zuul网关
public class ServiceLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLoginApplication.class, args);
    }

}
