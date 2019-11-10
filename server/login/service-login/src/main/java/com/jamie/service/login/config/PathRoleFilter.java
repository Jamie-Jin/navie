package com.jamie.service.login.config;

import com.jamie.service.login.dao.MenuDao;
import com.jamie.service.login.vo.RoleMenuVo;
import com.jamie.service.login.vo.RoleVo;
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
    private MenuDao menuDao;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 当前请求路径
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        // 获取所有的路径，以及路径对应的角色信息
        List<RoleMenuVo> roleMenuVos = menuDao.getRoleMenus();

        // 当前请求路径与所有的路径进行正则匹配，匹配上了，获取该路径对应的角色, 将角色信息写入Spring Security中
        for (RoleMenuVo roleMenuVo: roleMenuVos){
            if (antPathMatcher.match(roleMenuVo.getMenu(), requestUrl)){
                // 角色信息
                List<RoleVo> roles = roleMenuVo.getRoleVos();

                String[] roleStrs = new String[roles.size()];
                for (int i=0; i<roleStrs.length; i++){
                    roleStrs[i] = roles.get(i).getRole();
                }
                return SecurityConfig.createList(roleStrs);
            }
        }

        // TODO 没匹配上的路径, 跳转登录页？？
        return SecurityConfig.createList("ROLE_LOGIN");
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
