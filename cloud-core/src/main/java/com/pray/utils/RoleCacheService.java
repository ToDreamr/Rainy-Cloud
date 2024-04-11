package com.pray.utils;

import java.util.List;

/**
 * RoleCacheService
 *
 * @author 春江花朝秋月夜
 * @since 2023/12/7
 */
public interface RoleCacheService<T> {
    List<T> listRole(String key);
    T cacheValue(String key);

    Boolean setCacheValue(String key, String cacheData);
}
