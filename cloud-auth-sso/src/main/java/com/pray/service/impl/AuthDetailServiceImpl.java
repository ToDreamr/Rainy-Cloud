package com.pray.service.impl;

import com.pray.util.JwtUtil;
import com.pray.entity.auth.AuthInfoInTokenBO;
import com.pray.entity.auth.AuthUser;
import com.pray.exception.CloudServiceException;
import com.pray.mapper.AuthUserMapper;
import com.pray.model.AuthAccount;
import com.pray.service.AuthDetailService;
import com.pray.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AuthDetailServiceImpl
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/13 1:42
 */
@Service
public class AuthDetailServiceImpl implements AuthDetailService {
    @Resource
    private AuthUserMapper authUserMapper;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    JwtUtil jwtUtil;
    @Override
    public Result<AuthInfoInTokenBO> getAuthInfoByUserNameAndPassword(String inputUserName, String password) {
        //获取登录账户
        AuthAccount authAccount = authUserMapper.getAuthInfoByUserName(inputUserName);
        if (authAccount==null){
            throw new CloudServiceException("不存在这样的用户");
        }
        if (!passwordEncoder.matches(password,authAccount.getPassword())){
          throw new CloudServiceException("密码错误，异常的尝试");
        }
        //构建上下文保存登录对象
        AuthInfoInTokenBO tokenBO = new AuthInfoInTokenBO();
        AuthUser authUser = new AuthUser();

        authUser.setUserId((long) authAccount.getId());

        String accessToken = jwtUtil.createToken(authAccount.getUsername(),authAccount.getId());
        authUser.setAccessToken(accessToken);


        String refreshToken = jwtUtil.createRefreshToken();
        try {
            // TODO 完成刷新token
            tokenBO.setRefreshToken(refreshToken);
        } catch (Exception e) {
            throw new CloudServiceException("颁发或刷新Token异常");
        }
        tokenBO.setExpiresIn(3000);
        //授权登录用户
        tokenBO.setAuthUser(authUser);
        return Result.ok(tokenBO);
    }
}
