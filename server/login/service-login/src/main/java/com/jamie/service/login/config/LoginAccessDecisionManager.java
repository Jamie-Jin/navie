package com.jamie.service.login.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 将当前登录用户的角色和访问路径需要的角色比较，如果两者有交集，请求继续，如果两者无交集，就是非法请求
 */
@Component
public class LoginAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // collection: 当前访问路径需要的角色
        for (ConfigAttribute attribute: collection){
            // 说明该路径要登陆后才能访问
            if ("ROLE_LOGIN".equals(attribute.getAttribute())){
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("未登录，非法请求");
                }
                else {
                    // 请求继续
                    return;
                }
            }

            // 当前用户已有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority: authorities){
                // 当前用户已有角色与访问路径需要的角色一致
                if (authority.getAuthority().equals(attribute.getAttribute())){
                    // 请求继续
                    return;
                }
            }
        }
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
