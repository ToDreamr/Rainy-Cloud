package com.pray.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pray.entity.blog.User;
import com.pray.entity.dto.RegisterDto;
import com.pray.entity.Result;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
public interface IUserService extends IService<User> {
    Result<?> register(RegisterDto registerDto);

}
