package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 群聊记录
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("im_chat_user_group_message")
public class ImChatUserGroupMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 群ID
     */
    private Integer groupId;

    /**
     * 发送ID
     */
    private Integer fromId;

    /**
     * 接收ID
     */
    private Integer toId;

    /**
     * 内容
     */
    private String content;


    public static final String GROUP_ID = "group_id";

    public static final String FROM_ID = "from_id";

    public static final String TO_ID = "to_id";

    public static final String CONTENT = "content";

}
