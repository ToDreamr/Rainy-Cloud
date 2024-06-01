package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 好友
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("im_chat_user_friend")
public class ImChatUserFriend extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 好友ID
     */
    private Integer friendId;

    /**
     * 朋友状态[0:未审核，1:审核通过]
     */
    private Integer friendStatus;

    /**
     * 备注
     */
    private String remark;


    public static final String USER_ID = "user_id";

    public static final String FRIEND_ID = "friend_id";

    public static final String FRIEND_STATUS = "friend_status";

    public static final String REMARK = "remark";

}
