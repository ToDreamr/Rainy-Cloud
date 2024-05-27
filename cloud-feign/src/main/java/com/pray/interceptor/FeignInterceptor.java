package com.pray.interceptor;

/**
 * FeignInterceptor
 *
 * @author 春江花朝秋月夜
 * @since 2024/5/27 20:48
 */

import cn.dev33.satoken.same.SaSameUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
public class FeignInterceptor implements RequestInterceptor {

    /**
     * 为 Feign 的 RPC调用 添加请求头Token
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null){
            // 不是Web请求，使用SaToken提供的 SAME_TOKEN
            requestTemplate.header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());
            return;
        }
        // 获取 HttpServletRequest 对象
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 从请求头中获取Token信息
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        //添加token
        if (StringUtils.isEmpty(authorization)) {
            // 请求头没有Token时，使用SaToken提供的 SAME_TOKEN
            requestTemplate.header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());
        } else {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, authorization);
        }
    }
}
