package com.pray.model;

/**
 * AuthAccount
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/14 15:44
 */
public class AuthAccount {
    /**
     * 用户ID
     */
    private int id;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户是否启用
     */
    private int enabled;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建ip
     */
    private String createIp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "AuthAccount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createIp='" + createIp + '\'' +
                '}';
    }
}
