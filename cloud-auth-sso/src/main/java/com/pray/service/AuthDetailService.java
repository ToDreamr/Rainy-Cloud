package com.pray.service;

import com.pray.entity.auth.AuthUserTokenInfo;

/**
 * AuthDetailService
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/13
 */
public interface AuthDetailService {
    AuthUserTokenInfo getAuthInfoByUserNameAndPassword(String inputUserName, String password);
}
