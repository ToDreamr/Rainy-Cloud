package com.pray.utils.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pray.entity.po.Role;
import com.pray.utils.RoleCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleCacheServiceImpl
 *
 * @author 春江花朝秋月夜
 * @since 2023/12/7 0:32
 */
@Service
@Slf4j
public class RoleCacheServiceImpl implements RoleCacheService {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    private static final ObjectMapper mapper=new ObjectMapper();
    @Override
    public List<Role> listRole( String key) {
        log.info("接收到请求缓存key：{}",key);
        List<Role> listRole = (List<Role>) redisTemplate.opsForValue().get(key);
        return listRole;//不论是否是空都返回出去
    }

    /**
     * 获取缓存值
     * @param key
     * @return
     */
    @Override
    public Object cacheValue(String key) {
        return redisTemplate.opsForValue().get(key);
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
