package com.jamie.service.login.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败处理器
 * 为什么要登录失败还要跳转用户的目标路径？ 答：其实登录失败，目标路径是死活都跳不过去的，只能跳到登录页，
 * 之所以要配置目标路径，是为了应对【如果用户不小心输错登录信息导致登录失败，等他再次输入就正确】这种情况，
 * 这时因为登录成功，那肯定要跳到登陆前用户的请求路径啦！！
 * 其实原理还不懂，只知道要这样配....
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // 用于获取跳转登录页前的请求路径
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String targetUrl = "";
        if (savedRequest != null){
            // 目标路径
            targetUrl = savedRequest.getRedirectUrl();
        }

        // 重定向到跳转登录页前的请求路径
        response.sendRedirect(targetUrl);
    }

}
