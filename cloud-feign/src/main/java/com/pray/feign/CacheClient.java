package com.pray.feign;

import com.pray.config.DefaultFeignConfig;
import com.pray.feign.api.FeignApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * CacheClient
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/24
 */
@Service
@FeignClient(value = "cacheclient",configuration = DefaultFeignConfig.class)
public interface CacheClient extends FeignApi {

}
