package com.pray.entity.dto;

import lombok.Data;

/**
 * <p>
 * RegisterDto
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/29 20:02
 */
@Data
public class RegisterDto {
    /**
     * 用户名
     */
    public String username;

    /**
     * 密码
     */
    public String password;

    /**
     * 邮箱
     */
    public String email;

    /**
     * 验证码
     */
    public String code;
}
