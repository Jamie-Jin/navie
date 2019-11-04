package com.jamie.service.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableDiscoveryClient
public class ServiceLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLoginApplication.class, args);
    }

}
