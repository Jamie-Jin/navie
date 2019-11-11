package com.jamie.common_util.redis;

import lombok.Getter;

import java.time.Duration;

/**
 * Redis键常量
 */
@Getter
public class RedisConstant {

    // Redis默认键值对超时时间，单位：秒
    public static final Duration expire = Duration.ofSeconds(60);

    // 所有菜单（访问路径）以及菜单对应的角色
    // 使用@Cacheable有个坑：key必须加上''
    // 不然会报：cannot be found on object of type 'org.springframework.cache.interceptor.CacheExpressionRootObject
    public static final String allMenusRoles = "ALL_MENU_AND_ROLES";

    // 用户名
    public static final String user = "USER";

    // 菜单
    public static final String menu = "MENU";

}
