package com.pray.utils.cache;

import com.pray.constants.PrayConstants;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * RedisCacheUpdate
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/9/13 16:08
 */
@Data
public class RedisCacheUpdate implements Runnable, Delayed {
    private String redisKey;
    private RedisTemplate redisTemplate;
    private Object data;
    public RedisCacheUpdate(String redisKey,
                            RedisTemplate<Object, Object> stringRedisTemplate,
                            Object data) {
        this.redisKey = redisKey;
        this.redisTemplate=stringRedisTemplate;
        this.data=data;
    }
    @Override
    public void run() {
        //异步更新Redis缓存
        redisTemplate.opsForValue().set(redisKey,
                data,
                PrayConstants.RECENT_DAYS,
                TimeUnit.SECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    //如何解决高并发场景下的分布式锁的问题
    //方案1：非分布式锁：悲观锁和乐观锁，用版本号解决，维护一个版本号，这是JVM级别的锁
    //方案2：分布式锁：使用setNx命令，成功则获取锁，否则没有获取到锁，释放锁的时候需要判断锁的标识，同样是jvm级别的锁
}
