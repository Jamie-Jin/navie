package com.jamie.web.resource.filter;

import com.jamie.common_web.filter.WebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 资源模块拦截器
 */
@Configuration
public class WebResourceFilter extends WebMvcConfigurationSupport {

    // 公用拦截器
    @Bean
    public WebFilter webFilter(){
        return new WebFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // addPathPatterns: 要拦截的路径
        // excludePathPatterns：不需要拦截的路径
        registry.addInterceptor(webFilter()).addPathPatterns("/**").excludePathPatterns("");

        super.addInterceptors(registry);
    }

}
