package com.pray.utils.cache;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CacheUtil
 * @author Rainy-Heights
 */
public class CacheUtil {
    // 声明一个并发哈希Map，用于存储缓存中的键值对
    private final ConcurrentHashMap<String, CacheEntry<Object>> cache;
    // 定义一个long类型的变量maxAge
    private final long maxAge;

    private  List<String> keyList;
    public CacheUtil(long maxAge) {
        this.cache = new ConcurrentHashMap<>();
        this.maxAge = maxAge;
    }

    public void put(String key, Object value) {
        keyList.add(key);
        cache.put(key, new CacheEntry<>(value));
    }

    public Object get(String key) {
        CacheEntry<Object> entry = cache.get(key);
        if (entry == null) {
            return null;
        }

        long age = System.currentTimeMillis() - entry.getTimestamp();
        if (age > maxAge) {
            cache.remove(key);
            return null;
        }

        return entry.getValue();
    }
    public void remove(String key) {
        cache.remove(key);
    }

     // 定义一个名为CacheEntry的私有静态类
     private static class CacheEntry<T> {
            // 定义一个泛型Object类型的value变量
            private T value;
            // 定义一个long类型的timestamp变量
            private long timestamp;

            // 构造函数，传入一个Object类型的value参数
            public CacheEntry(T value) {
                // 将参数value赋值给value变量
                this.value = value;
                // 将当前时间戳赋值给timestamp变量
                this.timestamp = System.currentTimeMillis();
            }

            // 定义一个getValue方法，返回value变量
            public Object getValue() {
                return value;
            }

            // 定义一个getTimestamp方法，返回timestamp变量
            public long getTimestamp() {
                return timestamp;
            }
        }
}


