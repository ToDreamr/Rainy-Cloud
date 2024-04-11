package com.pray.feign;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ServerClientFallback
 *
 * @author 春江花朝秋月夜
 * @since 2024/2/19 14:50
 */

//使用Feign时熔断降级替代的方案
@Component
public class ServerClientFallback implements ServiceClient{
    @Override
    public Object CacheValue(String key) {
        return null;
    }

    @Override
    public Boolean CacheValue(String key, String cacheData) {
        return false;
    }

    @Override
    public List<Map<String, Object>> getBorrowUsers() {
        return null;
    }

    @Override
    public int insertBorrowRecord(int userId, int bookId) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectBorrowDetails(int userId, int bookId) {
        return null;
    }
}
