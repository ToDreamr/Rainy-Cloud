package com.pray.entity.bo;

/**
 * AuthUser
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/12 21:44
 */
public class AuthUser {
    /**
     * 用户在自己系统的用户id
     */
    private Long userId;
    private String accessToken;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
