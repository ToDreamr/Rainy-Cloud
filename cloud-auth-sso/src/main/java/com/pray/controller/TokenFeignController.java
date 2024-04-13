package com.pray.controller;

import com.pray.entity.bo.AuthUser;
import com.pray.exception.CloudServiceException;
import com.pray.feign.TokenFeignClient;
import com.pray.manager.TokenFactory;
import com.pray.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Override
    public Result<AuthUser> checkToken(@RequestParam("accessToken") String accessToken) {
        log.info("<--------------------解析Token-------------------------->");
        Result<AuthUser> authUser = tokenFactory.getAuthUser(accessToken);
        if (authUser.getCode() != 200) {
           throw new CloudServiceException("token校验失败");
        }
        return authUser;
    }
}
