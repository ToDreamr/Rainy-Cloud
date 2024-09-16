package com.pray.entity.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * AuthorizeVO 认证成功返回前端的统一信息
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/24
 */
@Data
public class AuthorizeVO {
    /**
     * 用户名
     */
    String username;
    /**
     * 过期时间
     */
    Date expireTime;
    /**
     * 用户token
     */
    String token;
}
