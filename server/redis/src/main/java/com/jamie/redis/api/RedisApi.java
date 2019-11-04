package com.jamie.redis.api;


import java.util.concurrent.TimeUnit;

public interface RedisApi {

    void set(String key, Object val, long expire);

    void set(String key, Object val, long expire, TimeUnit timeUnit);

    // 使键值对过期
    Boolean expire(String key, long expire);

    // 使键值对过期（可自定义时间单位）
    Boolean expire(String key, long expire, TimeUnit timeUnit);
}
