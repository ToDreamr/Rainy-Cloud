package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 聊天群成员
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("im_chat_group_user")
public class ImChatGroupUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 群ID
     */
    private Integer groupId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 审核用户ID
     */
    private Integer verifyUserId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否管理员[0:否，1:是]
     */
    private Boolean adminFlag;

    /**
     * 用户状态[0:未审核，1:审核通过，2:禁言]
     */
    private Integer userStatus;


    public static final String GROUP_ID = "group_id";

    public static final String USER_ID = "user_id";

    public static final String VERIFY_USER_ID = "verify_user_id";

    public static final String REMARK = "remark";

    public static final String ADMIN_FLAG = "admin_flag";

    public static final String USER_STATUS = "user_status";

}
