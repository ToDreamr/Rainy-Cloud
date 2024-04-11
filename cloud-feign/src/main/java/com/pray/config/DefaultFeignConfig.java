package com.pray.config;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DefaultFeignConfig
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/22 0:00
 */
@Configuration
public class DefaultFeignConfig extends FeignClientProperties.FeignClientConfiguration {
    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.HEADERS;
    }
}
