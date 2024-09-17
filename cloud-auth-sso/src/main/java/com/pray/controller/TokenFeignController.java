package com.pray.controller;

import com.pray.entity.auth.AuthUser;
import com.pray.exception.CloudException;
import com.pray.feign.TokenFeignClient;
import com.pray.feign.config.Auth;
import com.pray.manager.TokenFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TokenFeignController
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/12 22:12
 */
@RestController
@Slf4j
public class TokenFeignController implements TokenFeignClient {
    @Resource
    private TokenFactory tokenFactory;
    @GetMapping(value = Auth.CHECK_TOKEN_URI)
    @Override
    public AuthUser checkToken(String accessToken) {
        log.info("<--------------------解析Token-------------------------->");
        log.info(this.getClass().getName()+"获取到accessToken：{}",accessToken);
        try {
            AuthUser tokenSelectRes = tokenFactory.getAuthUser(accessToken);
            if (tokenSelectRes == null) {
                throw new CloudException("token校验失败");
            }
            return tokenSelectRes;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
