package com.pray.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * RegisterDto
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/29 20:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterDTO extends CommonDTO {
    /**
     * 用户名
     */
    public String username;

    /**
     * 密码
     */
    public String password;

    public String email;

    public String code;
}
