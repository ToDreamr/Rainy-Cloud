package com.pray.manager;

import com.pray.entity.bo.AuthInfoInTokenBO;
import com.pray.entity.bo.AuthUser;
import com.pray.utils.Result;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * TokenFactory 1. 登陆返回token 2. 刷新token 3. 清除用户过去token 4. 校验token
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/12 22:13
 */
@Component
@RefreshScope
public class TokenFactory {
    private static final Logger logger = LoggerFactory.getLogger(TokenFactory.class);

    @Resource(name = "cloud-redisTemplate")
    private  RedisTemplate<Object, Object> redisTemplate;

    private final RedisSerializer<Object> redisSerializer;

    private final StringRedisTemplate stringRedisTemplate;

    public TokenFactory(RedisSerializer<Object>
            redisSerializer, StringRedisTemplate stringRedisTemplate) {
        this.redisSerializer = redisSerializer;
        this.stringRedisTemplate = stringRedisTemplate;
    }
    /**
     * 将用户的部分信息存储在token中，并返回token信息
     * @param authUser 用户在token中的信息
     * @return token信息
     */
    public AuthInfoInTokenBO storeAccessToken(AuthUser authUser) {

        String token = authUser.getAccessToken();
        System.out.println("accessToken:"+token);
        AuthInfoInTokenBO authInfoInTokenBO = new AuthInfoInTokenBO();
        authInfoInTokenBO.setAccessToken(token);
        authInfoInTokenBO.setAuthUser(authUser);
        redisTemplate.opsForValue().set(token, authUser);
        return authInfoInTokenBO;
    }
    public Result<AuthUser> getAuthUser(String accessToken) {
        System.out.println("accessToken:"+accessToken);
        Object authUser = redisTemplate.opsForValue().get(accessToken);
        if (authUser == null) {
            return Result.fail("accessToken 已过期");
        }
        return Result.ok((AuthUser) authUser);
    }
}
