package com.jamie.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 注入fastJson2JsonRedisSerializer
     * @return
     */
    @Bean
    public FastJsonSerializer fastJsonSerializer(){
        return new FastJsonSerializer<>(Object.class);
    }

    /**
     * 配置RedisTemplate
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        // 字符串类型序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // TODO Redis还能开启事务！！！
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(fastJsonSerializer());

        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonSerializer());

        // TODO 网上说法，必须执行这个函数来初始化RedisTemplate
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
