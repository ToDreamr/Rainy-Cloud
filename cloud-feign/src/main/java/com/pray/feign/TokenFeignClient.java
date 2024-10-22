package com.pray.feign;

import com.pray.entity.auth.AuthUser;
import com.pray.feign.config.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * TokenFeignClient
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/12
 */
@FeignClient(value = "token-service",contextId ="token")
public interface TokenFeignClient {
    @GetMapping(value = Auth.CHECK_TOKEN_URI)
    AuthUser checkToken(@RequestHeader(name = "authorization",required = true)
                                String accessToken);
}
