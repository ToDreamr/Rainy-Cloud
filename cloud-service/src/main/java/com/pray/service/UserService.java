package com.pray.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pray.entity.po.Account;

import java.util.List;

/**
 * UserService
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/24
 */
public interface UserService extends IService<Account> {
    List<Account>getUserList();
}
