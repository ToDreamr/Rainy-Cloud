package com.pray.controller;


import com.pray.entity.dto.AuthenticationDTO;
import com.pray.entity.dto.RegisterDto;
import com.pray.service.IUserService;
import com.pray.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;
    @PostMapping("/ua/login")
    public Result<AuthenticationDTO> login(
            @RequestBody AuthenticationDTO authenticationDTO) {
        return Result.ok(authenticationDTO);
    }
    //抽象出新的实体来接受用户传入的数据流
    @PostMapping("/ua/register")
    public Result<?> register(@RequestBody RegisterDto registerDto){
        return Result.ok(userService.register(registerDto));
    }
}

