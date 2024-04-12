package com.pray.utils;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * RoleCacheService
 *
 * @author 春江花朝秋月夜
 * @since 2023/12/7
 */
public interface RoleCacheService<T> {
    T cacheValue(String key);

    <ID> Object cacheValue(String key, ID id, Class<Object> type,
                           Function<ID, Object> callBack,
                           Long time, TimeUnit unit);

    Boolean setCacheValue(String key, String cacheData);
}
