package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 聊天群
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("im_chat_group")
public class ImChatGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 群名称
     */
    private String groupName;

    /**
     * 群主用户ID
     */
    private Integer masterUserId;

    /**
     * 群头像
     */
    private String avatar;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 公告
     */
    private String notice;

    /**
     * 进入方式[0:无需验证，1:需要群主或管理员同意]
     */
    private Boolean inType;

    /**
     * 类型[1:聊天群，2:话题]
     */
    private Integer groupType;


    public static final String GROUP_NAME = "group_name";

    public static final String MASTER_USER_ID = "master_user_id";

    public static final String AVATAR = "avatar";

    public static final String INTRODUCTION = "introduction";

    public static final String NOTICE = "notice";

    public static final String IN_TYPE = "in_type";

    public static final String GROUP_TYPE = "group_type";

}
