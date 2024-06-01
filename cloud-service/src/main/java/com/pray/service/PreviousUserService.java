package com.pray.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pray.entity.po.Account;

import java.util.List;

/**
 * PreviousUserService
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/24
 */
public interface PreviousUserService extends IService<Account> {
    List<Account>getUserList();
}
