package com.jamie.common_util.redis;


/**
 * 拼接Redis键
 */
public class RedisKeyUtil {

    // 用户键
    public static String user(String name){
        return RedisConstant.user + name;
    }

}
