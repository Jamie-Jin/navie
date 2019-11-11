package com.jamie.service.login.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 自定义决策器
 * 将当前登录用户的角色和访问路径需要的角色比较，如果两者有交集，请求继续，如果两者无交集，就是非法请求
 */
@Component
public class LoginAccessDecisionManager implements AccessDecisionManager {
    private static final Logger logger = LoggerFactory.getLogger(LoginAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object requestUrl, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (collection == null){
            return;
        }

        logger.info("当前访问路径：" + requestUrl.toString());

        // 当前请求路径需要的角色（一个请求可能对应多个角色）
        for (ConfigAttribute configAttribute: collection){
            // 角色
            String needRole = configAttribute.getAttribute();

            logger.info(requestUrl.toString() + "需要的权限：" + needRole);

            // 当前登录用户拥有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority: authorities){
                // 请求路径需要的角色 == 当前用户拥有的角色
                if (needRole.equals(authority.getAuthority())){
                    // 验证通过
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限访问路径");
    }

    // TODO 之前是return false 为什么要return true
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    // TODO 之前是return false 为什么要return true
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
