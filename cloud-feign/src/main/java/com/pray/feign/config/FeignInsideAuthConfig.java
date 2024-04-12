package com.pray.feign.config;

import org.springframework.stereotype.Component;

/**
 * FeignInsideAuthConfig
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/12 21:51
 */
@Component
public class FeignInsideAuthConfig {
    public static final String FEIGN_INSIDE_URL_PREFIX = "/feign";
    public String key="feign-inside-auth";
    private String secret;

    public String getSecret() {
        return secret;
    }
    public String getKey() {
        return key;
    }
}
