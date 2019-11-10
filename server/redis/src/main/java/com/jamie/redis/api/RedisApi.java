package com.jamie.redis.api;


import java.util.concurrent.TimeUnit;

public interface RedisApi {

    // 获取键值对
    Object get(String key);

    // 设置键值对，使用默认的超时时间（定义在RedisConstant中）
    void set(String key, Object val);

    // 设置键值对，超时时间单位：秒
    void set(String key, Object val, long expire);

    // 设置键值对，超时时间单位可自定义
    void set(String key, Object val, long expire, TimeUnit timeUnit);

    // 为键值对设置过期时间，时间单位：秒
    Boolean expire(String key, long expire);

    // 为键值对设置过期时间，时间单位可自定义
    Boolean expire(String key, long expire, TimeUnit timeUnit);

}
