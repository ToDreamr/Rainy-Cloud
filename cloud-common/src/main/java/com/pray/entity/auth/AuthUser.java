package com.pray.entity.auth;

import com.pray.entity.sys.SysUser;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * AuthUser 已经完成认证的用户
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/12 21:44
 */
@Data
public class AuthUser implements Serializable {
    /**
     * 用户在自己系统的用户id
     */
    private Long userId;
    private String accessToken;

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private SysUser user;

    /**
     * 角色列表
     */
    private Set<String> roles;


    public AuthUser() {
    }

    public AuthUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    public AuthUser(Long userId, Long deptId, SysUser user, Set<String> permissions) {
        this.userId = userId;
        this.deptId = deptId;
        this.user = user;
        this.permissions = permissions;
    }
}
