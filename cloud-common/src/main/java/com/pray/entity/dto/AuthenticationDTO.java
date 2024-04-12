package com.pray.entity.dto;

/**
 * AuthenticationDTO
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/13 1:36
 */
public class AuthenticationDTO {
    /**
     * 用户名
     */
    protected String principal;

    /**
     * 密码
     */
    protected String credentials;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "AuthenticationDTO{" +
                "principal='" + principal + '\'' +
                ", credentials='" + credentials + '\'' +
                '}';
    }
}
