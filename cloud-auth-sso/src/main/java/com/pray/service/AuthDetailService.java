package com.pray.service;

import com.pray.entity.auth.AuthInfoInTokenBO;

/**
 * AuthDetailService
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/13
 */
public interface AuthDetailService {
    AuthInfoInTokenBO getAuthInfoByUserNameAndPassword(String inputUserName,String password);
}
