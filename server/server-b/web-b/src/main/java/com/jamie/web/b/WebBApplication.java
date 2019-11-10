package com.jamie.web.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableFeignClients(basePackages = "com.jamie") //开启Feign远程调用
@EnableDiscoveryClient      //在注册中心上注册
public class WebBApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBApplication.class, args);
    }

}
