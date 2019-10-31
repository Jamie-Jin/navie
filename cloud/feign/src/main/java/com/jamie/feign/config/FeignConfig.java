package com.jamie.feign.config;

import feign.Feign;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class FeignConfig {

    @Bean
    @Primary
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new ErrorDecoder.Default();
    }

}
