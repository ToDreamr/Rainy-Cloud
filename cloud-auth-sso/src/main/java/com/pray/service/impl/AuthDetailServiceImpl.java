package com.pray.service.impl;

import com.pray.entity.auth.AuthInfoInTokenBO;
import com.pray.entity.auth.AuthUser;
import com.pray.entity.sys.SysUser;
import com.pray.exception.CloudException;
import com.pray.mapper.AuthUserMapper;
import com.pray.model.AuthAccount;
import com.pray.service.AuthDetailService;
import com.pray.util.JwtUtil;
import com.pray.utils.ip.IpUtils;
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

    /**
     * 用户登录逻辑
     * @param inputUserName 用户名
     * @param password 密码
     * @return AuthInfoInTokenBO保存在token信息里面的信息，包含已认证用户AuthUser，刷新token，token过期时间
     */
    @Override
    public AuthInfoInTokenBO getAuthInfoByUserNameAndPassword(String inputUserName, String password) {
        //获取登录账户
        AuthAccount authAccount = authUserMapper.getAuthInfoByUserName(inputUserName);
        if (authAccount==null){
            throw new CloudException("不存在这样的用户");
        }
        if (!passwordEncoder.matches(password,authAccount.getPassword())){
          throw new CloudException("密码错误，异常的尝试");
        }
        //构建上下文保存登录对象
        AuthInfoInTokenBO tokenBO = new AuthInfoInTokenBO();

        //已认证用户
        AuthUser authUser = new AuthUser();

        //用户ID
        authUser.setUserId((long) authAccount.getId());
        //用户IP
        authUser.setIpaddr(IpUtils.getIpAddr());
        authUser.setOs("Windows/11.0");
        authUser.setExpireTime(3000L);
        authUser.setLoginLocation(IpUtils.getHostName());
        authUser.setUser(new SysUser());
        //根据用户信息创建token
        String token = jwtUtil.createToken(authAccount.getUsername(),authAccount.getId());
        authUser.setAccessToken(token);

        String refreshToken = jwtUtil.createRefreshToken();
        try {
            // TODO 完成刷新token
            tokenBO.setRefreshToken(refreshToken);
        } catch (Exception e) {
            throw new CloudException("颁发或刷新Token异常");
        }
        tokenBO.setExpiresIn(3000);
        //授权登录用户
        tokenBO.setAuthUser(authUser);
        return tokenBO;
    }
}
