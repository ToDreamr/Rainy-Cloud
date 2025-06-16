package com.pray.entity.po;


import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户信息表
 *
 * @TableName user
 */
@TableName(keepGlobalPrefix = true)
public class User implements Serializable {

    /**
     * id
     */

    private Integer id;
    /**
     * 用户名
     */

    private String username;
    /**
     * 密码
     */

    private String password;
    /**
     * 手机号
     */

    private String phoneNumber;
    /**
     * 用户邮箱
     */

    private String email;
    /**
     * 是否启用[0:否，1:是]
     */

    private Integer userStatus;
    /**
     * 性别[1:男，2:女，0:保密]
     */

    private Integer gender;
    /**
     * openId
     */

    private String openId;
    /**
     * 头像
     */

    private String avatar;
    /**
     * 赞赏
     */
    private String admire;
    /**
     * 订阅
     */

    private String subscribe;
    /**
     * 简介
     */

    private String introduction;
    /**
     * 用户类型[0:admin，1:管理员，2:普通用户]
     */

    private Integer userType;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 最终修改时间
     */

    private Date updateTime;
    /**
     * 最终修改人
     */

    private String updateBy;
    /**
     * 是否启用[0:未删除，1:已删除]
     */

    private Integer deleted;

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 是否启用[0:否，1:是]
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 性别[1:男，2:女，0:保密]
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 赞赏
     */
    public void setAdmire(String admire) {
        this.admire = admire;
    }

    /**
     * 订阅
     */
    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * 简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 用户类型[0:admin，1:管理员，2:普通用户]
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最终修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 最终修改人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 是否启用[0:未删除，1:已删除]
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }


    /**
     * id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 手机号
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * 用户邮箱
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 是否启用[0:否，1:是]
     */
    public Integer getUserStatus() {
        return this.userStatus;
    }

    /**
     * 性别[1:男，2:女，0:保密]
     */
    public Integer getGender() {
        return this.gender;
    }

    /**
     * openId
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * 头像
     */
    public String getAvatar() {
        return this.avatar;
    }

    /**
     * 赞赏
     */
    public String getAdmire() {
        return this.admire;
    }

    /**
     * 订阅
     */
    public String getSubscribe() {
        return this.subscribe;
    }

    /**
     * 简介
     */
    public String getIntroduction() {
        return this.introduction;
    }

    /**
     * 用户类型[0:admin，1:管理员，2:普通用户]
     */
    public Integer getUserType() {
        return this.userType;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 最终修改时间
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 最终修改人
     */
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     * 是否启用[0:未删除，1:已删除]
     */
    public Integer getDeleted() {
        return this.deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userStatus=" + userStatus +
                ", gender=" + gender +
                ", openId='" + openId + '\'' +
                ", avatar='" + avatar + '\'' +
                ", admire='" + admire + '\'' +
                ", subscribe='" + subscribe + '\'' +
                ", introduction='" + introduction + '\'' +
                ", userType=" + userType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
