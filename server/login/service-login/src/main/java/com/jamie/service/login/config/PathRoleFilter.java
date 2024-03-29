package com.jamie.service.login.config;

import com.jamie.service.login.biz.MenuBiz;
import com.jamie.api.login.vo.RoleMenuVo;
import com.jamie.api.login.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 根据访问路径获取该路径需要的角色（使用拦截器）
 */
@Component
public class PathRoleFilter implements FilterInvocationSecurityMetadataSource {

    //  路径匹配工具
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private MenuBiz menuBiz;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 当前请求路径
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        // 获取所有的请求路径，以及路径对应的角色信息
        List<RoleMenuVo> roleMenuVos = menuBiz.getRoleMenus();

        // 当前请求路径与所有的路径进行正则匹配，匹配上了，获取该路径对应的角色, 将角色信息写入Spring Security中
        for (RoleMenuVo roleMenuVo: roleMenuVos){
            if (antPathMatcher.match(roleMenuVo.getMenu(), requestUrl)){
                // 角色信息
                List<RoleVo> roles = roleMenuVo.getRoleVos();

                // Spring Security要的是字符串数组，而非集合，顶....
                String[] roleStrs = new String[roles.size()];
                for (int i=0; i<roleStrs.length; i++){
                    // 只要权限，不要id，权限中文名
                    roleStrs[i] = roles.get(i).getRole();
                }

                // 将当前请求路径所需的角色写入Spring Security中
                return SecurityConfig.createList(roleStrs);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    // TODO 之前是return false，为什么要写return true
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
