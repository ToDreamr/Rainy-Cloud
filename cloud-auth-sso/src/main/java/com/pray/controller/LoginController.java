package com.pray.controller;

import com.pray.entity.auth.AuthInfoInTokenBO;
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
    TokenFactory tokenFactory;
    @Resource
    AuthDetailService authDetailService;
    @PostMapping("/ua/login")
    public Result<AuthInfoInTokenBO> login(
             @RequestBody AuthenticationDTO authenticationDTO) {
        //尝试登录
        AuthInfoInTokenBO info =
                authDetailService.getAuthInfoByUserNameAndPassword(authenticationDTO.getCredentials(), authenticationDTO.getPrincipal());

        AuthInfoInTokenBO authInfoInTokenBO = tokenFactory.storeAccessToken(info.getAuthUser());
        return Result.success(authInfoInTokenBO);
    }
}
