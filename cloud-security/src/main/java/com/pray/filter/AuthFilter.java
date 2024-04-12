package com.pray.filter;

import cn.hutool.core.util.StrUtil;
import com.pray.auth.AuthUserContext;
import com.pray.entity.bo.AuthUser;
import com.pray.feign.TokenFeignClient;
import com.pray.feign.config.Auth;
import com.pray.feign.config.FeignInsideAuthConfig;
import com.pray.handler.HttpHandler;
import com.pray.utils.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * AuthFilter
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/12 21:39
 */
@Component
public class AuthFilter implements Filter {
    @Resource
    private FeignInsideAuthConfig feignInsideAuthConfig;
    @Resource
    private HttpHandler httpHandler;
    @Resource
    TokenFeignClient tokenFeignClient;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    //检查feign请求
    private boolean feignRequestCheck(HttpServletRequest req) {
        // 不是feign请求，不用校验
        if (!req.getRequestURI().startsWith(FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX)) {
            return true;
        }
        String feignInsideSecret = req.getHeader(feignInsideAuthConfig.getKey());

        // 校验feign 请求携带的key 和 value是否正确
        return !StrUtil.isBlank(feignInsideSecret) && Objects.equals(feignInsideSecret, feignInsideAuthConfig.getSecret());
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        if (!feignRequestCheck(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        //如果是token校验请求，放行
        if (Auth.CHECK_TOKEN_URI.equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
        }
        String accessToken = request.getHeader("Authorization");
        if (StrUtil.isBlank(accessToken)) {
            httpHandler.printServerResponseToWeb(Result.fail(HttpServletResponse.SC_UNAUTHORIZED, "未登录"));
        }
        //成功获取了token，从token中解析用户信息
        AuthUser authUser = tokenFeignClient.checkToken(accessToken).getData();

        //保存登录用户的应用上下文
        try {
            AuthUserContext.set(authUser);
            filterChain.doFilter(request, response);
        }finally {
            AuthUserContext.remove();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
