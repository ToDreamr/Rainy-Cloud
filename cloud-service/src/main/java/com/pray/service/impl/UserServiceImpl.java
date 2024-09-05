package com.pray.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pray.constants.PrayConstants;
import com.pray.entity.blog.User;
import com.pray.entity.dto.RegisterDto;
import com.pray.entity.vo.response.AuthorizeVO;
import com.pray.mapper.UserMapper;
import com.pray.service.IUserService;
import com.pray.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    UserMapper userMapper;
    @Override
    public Result<?> register(RegisterDto registerDto) {
        //用户是否存在
        List<User> list = query().eq("username", registerDto.getUsername())
                .eq("email", registerDto.getEmail()).list();
        if (!list.isEmpty()){
            return Result.fail("用户已存在，请前往登录");
        }
        //验证验证码逻辑
        AuthorizeVO authorizeVO= new AuthorizeVO();//注册成功的验证实体
        authorizeVO.setUsername(registerDto.getUsername());
        authorizeVO.setExpireTime(DateUtil.date(PrayConstants.LOGIN_USER_TTL));
        return Result.ok(authorizeVO);
    }
}
