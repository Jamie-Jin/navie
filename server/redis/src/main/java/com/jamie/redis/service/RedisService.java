package com.jamie.redis.service;

import com.jamie.common_util.redis.RedisConstant;
import com.jamie.redis.api.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService implements RedisApi {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 获取键值对
    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 设置键值对，使用默认的超时时间
    @Override
    public void set(String key, Object val) {
        //set(key, val, RedisConstant.expire);
    }

    // 设置键值对，超时时间单位：秒
    @Override
    public void set(String key, Object val, long expire) {
        set(key, val, expire, TimeUnit.SECONDS);
    }

    // 设置键值对，超时时间单位可自定义
    @Override
    public void set(String key, Object val, long expire, TimeUnit timeUtil) {
        redisTemplate.opsForValue().set(key, val, expire, timeUtil);
    }

    // 为键值对设置过期时间，时间单位：秒
    @Override
    public Boolean expire(String key, long expire)  {
        return expire(key, expire, TimeUnit.SECONDS);
    }

    // 为键值对设置过期时间，时间单位可自定义
    @Override
    public Boolean expire(String key, long expire, TimeUnit timeUnit) {
        Boolean result = false;
        try {
            result = redisTemplate.expire(key, expire, timeUnit);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


}
