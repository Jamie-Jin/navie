package com.jamie.web.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableFeignClients(basePackages = "com.jamie")
public class WebAApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAApplication.class, args);
    }

}
