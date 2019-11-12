package com.jamie.common_web.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义公用拦截器
 */
public class WebFilter extends HandlerInterceptorAdapter {

    // 静态资源路径
    @Value("${static.url}")
    private String staticUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("static", staticUrl);

        // 此句为直接pass，不拦截
        return super.preHandle(request, response, handler);
    }

}
