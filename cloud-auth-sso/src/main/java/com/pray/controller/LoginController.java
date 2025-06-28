package com.pray.controller;

import com.pray.entity.auth.AuthUserTokenInfo;
import com.pray.entity.dto.AuthenticationDTO;
import com.pray.manager.TokenFactory;
import com.pray.service.AuthDetailService;
import com.pray.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoginController
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/13 1:33
 */
@RestController
public class LoginController {
    @Resource
    private TokenFactory tokenFactory;

    @Resource
    private AuthDetailService authDetailService;

    @PostMapping("/ua/login")
    public Result<AuthUserTokenInfo> login(
             @RequestBody AuthenticationDTO authenticationDTO) {
        //尝试登录
        AuthUserTokenInfo info =
                authDetailService.getAuthInfoByUserNameAndPassword(authenticationDTO.getCredentials(), authenticationDTO.getPrincipal());

        AuthUserTokenInfo authUserTokenInfo = tokenFactory.storeAccessToken(info.getAuthUser());
        return Result.success(authUserTokenInfo);
    }
}
