package com.jamie.redis.service;

import com.jamie.redis.api.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService implements RedisApi {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object val, long expire) {
        redisTemplate.opsForValue().set(key, val, expire, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object val, long expire, TimeUnit timeUtil) {
        redisTemplate.opsForValue().set(key, val, expire, timeUtil);
    }

    @Override
    public Boolean expire(String key, long expire)  {
        Boolean result = false;
        try {
            result =  redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

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
