package com.pray.utils.cache;

import cn.hutool.core.util.BooleanUtil;
import com.pray.entity.dto.RedisData;
import com.pray.constants.PrayConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * <p>
 * PrayCacheClient 基于RedisTemplate封装一套缓存工具类，解决缓存穿透，缓存雪崩，缓存击穿
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/9/14 17:14
 */
@Slf4j
@Component
public class PrayCacheClient {
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    public void set(String key, Object value, Long time, TimeUnit unit){
        redisTemplate.opsForValue().set(key,value,time,unit);
    }

    /**
     * 存储数据同时设置逻辑过期时间
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void setWithExpire(String key, Object value, Long time, TimeUnit unit){
        RedisData redisData = new RedisData();
        redisData.setData(value);
        //转换传入时间为秒，设置过期时间
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        redisTemplate.opsForValue().set(key,redisData);
    }

    /**
     * 缓存空对象解决缓存穿透
     * @param keyPrefix
     * @param id
     * @param type
     * @param callBack
     * @param time
     * @param unit
     * @return
     * @param <ID>
     */
    public <ID> Object  PassThrough(String keyPrefix, ID id, Class<Object> type, Function<ID,Object> callBack,
                                    Long time,TimeUnit unit)    {
          String key=keyPrefix+id;
          //查询缓存数据
         Object cacheValue = redisTemplate.opsForValue().get(key);
         assert cacheValue != null;
         if (!cacheValue.equals("")) {
              return cacheValue;
         }
          //函数逻辑，根据id查数据
          Object o=callBack.apply(id);
          if (o==null){
              //缓存空对象
              redisTemplate.opsForValue().set(key,"", PrayConstants.NULL_TTL,TimeUnit.MINUTES);
              return null;
          }
          this.set(key,o,time,unit);
          return o;
    }
    public <ID> Object LogicalExpire(String keyPrefix,ID id,Class<Object> type,Function<ID,Object> callback,
                                     Long time,TimeUnit unit){
        String key = keyPrefix + id;
        Object o = redisTemplate.opsForValue().get(key);
        if (o==null){
            return null;
        }
        RedisData redisData=(RedisData) o;
        Object cacheValue=redisData.getData();
        LocalDateTime expireTime = redisData.getExpireTime();

        if (expireTime.isAfter(LocalDateTime.now())){
            //缓存未过期，直接返回数据
            return cacheValue;
        }
        //缓存过期
        String lock=PrayConstants.TYPE_LOCK+id;
        if (tryLock(lock)){
            CACHE_REBUILD_EXECUTOR.submit(()->{
               try{
                   //查询数据库，重建缓存数据
                   Object applyValue = callback.apply(id);
                   //重建缓存
                   this.setWithExpire(key,applyValue,time,unit);
               }catch (Exception e){
                   throw new RuntimeException(e);
               }finally {
                  unLock(lock);
               }
            });
        }
        return cacheValue;
    }
    private boolean tryLock(String key) {
        //调用setIfNx方法
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }
    private void unLock(String key){
        redisTemplate.delete(key);
    }
}
