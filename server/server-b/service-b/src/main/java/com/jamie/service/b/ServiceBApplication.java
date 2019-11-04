package com.jamie.service.b;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableFeignClients(basePackages = "com.jamie") //开启Feign远程调用
@EnableDiscoveryClient              //声明为注册中心客户端
@EnableDistributedTransaction       //开启TX-LCN分布式事务
public class ServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBApplication.class, args);
    }

}
