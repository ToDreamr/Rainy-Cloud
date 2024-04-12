package com.pray.config;

import com.pray.filter.AuthFilter;
import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * AuthConfig 授权配置
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/13 0:32
 */
@Component
@Slf4j
public class AuthConfig {
    @Bean
    @Lazy
    public FilterRegistrationBean<AuthFilter> filterRegistration(AuthFilter authFilter) {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        // 添加过滤器
        registration.setFilter(authFilter);
        // 设置过滤路径，/*所有路径
        log.info("<----------------------- 加入拦截路径：----------------------->");
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        // 设置优先级
        registration.setOrder(-120);
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        return registration;
    }
}
