package com.jamie.web.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableFeignClients(basePackages = "com.jamie")
public class WebResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebResourceApplication.class, args);
    }

}
