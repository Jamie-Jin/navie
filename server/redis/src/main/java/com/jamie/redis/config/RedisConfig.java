package com.jamie.redis.config;

import com.jamie.common_util.redis.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
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

    /**
     * 设置Redis键值对的默认超时时间
     * @return
     */
    @Bean
    public CacheManager cacheManager(){
        // 自定义Redis键值对超时时间
        Duration expire = RedisConstant.expire;

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expire))
                .transactionAware()
                .build();
    }

}
