package com.pray.utils.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pray.utils.RoleCacheService;
import com.pray.utils.cache.PrayCacheClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * RoleCacheServiceImpl
 *
 * @author 春江花朝秋月夜
 * @since 2023/12/7 0:32
 */
@Service
@Slf4j
public class RoleCacheServiceImpl implements RoleCacheService<Object> {

    @Resource(name = "cloud-redisTemplate")
    RedisTemplate<Object,Object> redisTemplate;
    @Resource
    PrayCacheClient prayCacheClient;
    private static final ObjectMapper MAPPER =new ObjectMapper();

    @Override
    public Object cacheValue(String key) {
        if (redisTemplate.opsForValue().get(key)==null){
            setCacheValue(key,"");
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存值
     * @param key
     * @return
     */
    @Override
    public <ID> Object cacheValue(String key, ID id, Class<Object> type,
                                  Function<ID, Object> callBack,
                                  Long time, TimeUnit unit) {
        return prayCacheClient.passThrough(key,id,type,callBack,time,unit);
    }

    /**
     * 设置缓存值
     * @param key 缓存键
     * @param cacheData 缓存数据
     * @return 是否写入成功
     */
    @Override
    public Boolean setCacheValue(String key, String cacheData) {
        return redisTemplate.opsForValue().setIfAbsent(key,cacheData);
    }

}
