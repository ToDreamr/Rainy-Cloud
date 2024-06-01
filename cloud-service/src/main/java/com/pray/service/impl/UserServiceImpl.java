package com.pray.service.impl;

import com.pray.entity.blog.User;
import com.pray.mapper.UserMapper;
import com.pray.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
