package com.pray.entity.blog;

import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否启用【0：否，1：是】
     */
    private Boolean userStatus;

    /**
     * 性别【1：男，2：女，0：保密】
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
     * 电话号码
     */
    private String phoneNumber;

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
     * 用户类型；【0：admin;1:管理员；2:普通用户】
     */
    private Integer userType;


    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String EMAIL = "email";

    public static final String USER_STATUS = "user_status";

    public static final String GENDER = "gender";

    public static final String OPEN_ID = "open_id";

    public static final String AVATAR = "avatar";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String ADMIRE = "admire";

    public static final String SUBSCRIBE = "subscribe";

    public static final String INTRODUCTION = "introduction";

    public static final String USER_TYPE = "user_type";

}
