package com.pray.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pray.entity.po.Account;
import com.pray.service.UserService;
import com.pray.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/24 11:43
 */
@RestController
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/userlist")
    public Result<List<Account>> getUserList() {
        return Result.ok(userService.getUserList());
    }
}
