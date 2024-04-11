package com.pray.feign;

import com.pray.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * ServiceClient
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/24
 */
@Service
@FeignClient(value = "cloudcore",configuration = DefaultFeignConfig.class,fallback = ServerClientFallback.class )
public interface ServiceClient {
    /**
     * 获取缓存值
     * @param key
     * @return
     */
    @GetMapping(value = "/redis/cache/role/cacheValue")
    Object CacheValue(@RequestParam(value = "key") String key);
    /**
     * 设置缓存值
     * @param key 缓存键
     * @param cacheData 缓存数据
     * @return 是否写入成功
     */
    @PostMapping(value = "/redis/cache/role/setCacheValue")
    Boolean CacheValue(@RequestParam(value = "key") String key, @RequestParam(value = "cacheData") String cacheData);

    @GetMapping(value = "/redis/borrow/borrowUsers")
    List<Map<String,Object>> getBorrowUsers();

    @PostMapping("/redis/borrow/insertBorrow/{userId}/{bookId}")
    int insertBorrowRecord(@PathVariable(value = "userId") int userId, @PathVariable(value = "bookId") int bookId);

    @GetMapping("/redis/borrow/borrowDetails/{userId}/{bookId}")
    List<Map<String, Object>> selectBorrowDetails(@PathVariable(value = "userId") int userId, @PathVariable(value = "bookId") int bookId);
}
