package com.jamie.service.login.config;

import com.jamie.service.login.biz.UserDetailBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Spring Security配置类
 */
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    // 获取当前登陆用户的用户信息及角色
    @Autowired
    private UserDetailBiz userDetailBiz;

    // 根据访问路径获取该路径需要的角色
    @Autowired
    private PathRoleFilter pathRoleFilter;

    // 自定义决策器，将当前登录用户的角色和访问路径需要的角色比较
    @Autowired
    private LoginAccessDecisionManager loginAccessDecisionManager;

    // 自定义登录成功处理器（用于在用户登录成功后，拿回跳转登录页之前的请求路径）
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    // 自定义登录失败处理器（并不是登录失败就跳转登录页喔，还是要重定向到用户请求的路径，但别担心，登录失败时，是无法跳转到用户请求路径，只会去到登录页）
    @Autowired
    private LoginFailHandler loginFailHandler;

    // 密码加密解密工具
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Spring Security获取当前登录用户的用户信息以及角色信息（从数据库获取）
        auth.userDetailsService(userDetailBiz)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().antMatchers().permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o){
                        // 自定义决策器
                        o.setAccessDecisionManager(loginAccessDecisionManager);
                        // todo
                        o.setSecurityMetadataSource(pathRoleFilter);
                        return o;
                    }
                })
                .and()
                .formLogin()        // todo 以下是猜测：Spring Security的配置有顺序之分，formLogin如果放在拦截器，决策器前面，它们将不生效
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
                .and()
                .csrf().disable();
    }

}
