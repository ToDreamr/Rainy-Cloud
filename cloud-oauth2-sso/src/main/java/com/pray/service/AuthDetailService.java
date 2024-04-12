package com.pray.service;

import com.pray.entity.bo.AuthInfoInTokenBO;
import com.pray.utils.Result;

/**
 * AuthDetailService
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/13
 */
public interface AuthDetailService {
    Result<AuthInfoInTokenBO> getAuthInfoByUserNameAndPassword
            (String inputUserName,String password);
}
