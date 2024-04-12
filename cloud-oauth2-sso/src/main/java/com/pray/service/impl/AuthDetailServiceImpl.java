package com.pray.service.impl;

import cn.hutool.core.util.IdUtil;
import com.pray.entity.bo.AuthInfoInTokenBO;
import com.pray.entity.bo.AuthUser;
import com.pray.service.AuthDetailService;
import com.pray.utils.Result;
import org.springframework.stereotype.Service;

/**
 * AuthDetailServiceImpl
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/13 1:42
 */
@Service
public class AuthDetailServiceImpl implements AuthDetailService {
    @Override
    public Result<AuthInfoInTokenBO> getAuthInfoByUserNameAndPassword(String inputUserName, String password) {
        AuthInfoInTokenBO tokenBO = new AuthInfoInTokenBO();
        AuthUser authUser = new AuthUser();
        authUser.setUserId(1314L);

        String accessToken = IdUtil.simpleUUID();
        tokenBO.setAccessToken(accessToken);
        authUser.setAccessToken(accessToken);
        String refreshToken = IdUtil.simpleUUID();
        tokenBO.setRefreshToken(refreshToken);
        tokenBO.setExpiresIn(3000);
        tokenBO.setAuthUser(authUser);

        return Result.ok(tokenBO);
    }
}
