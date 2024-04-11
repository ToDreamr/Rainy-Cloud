package com.pray.config;

import com.pray.entity.po.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

/**
 * RedisConfig
 *
 * @author 春江花朝秋月夜
 * @since 2024/3/30 15:31
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, List<Account>> accountRedisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, List<Account>> template = new RedisTemplate<>();
        // 关联
        template.setConnectionFactory(connectionFactory);
        // 设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(List.class));
        return template;
    }
    //序列化RedisTemplate
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //解析器链接工厂
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}
