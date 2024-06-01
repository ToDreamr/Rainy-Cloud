package com.pray.entity.blog;

import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文章评论表
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论来源标识
     */
    private Integer source;

    /**
     * 评论来源类型
     */
    private String type;

    /**
     * 父评论ID
     */
    private Integer parentCommentId;

    /**
     * 发表用户ID
     */
    private Integer userId;

    /**
     * 楼层评论ID
     */
    private Integer floorCommentId;

    /**
     * 父发表用户名ID
     */
    private Integer parentUserId;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论额外信息
     */
    private String commentInfo;


    public static final String SOURCE = "source";

    public static final String TYPE = "type";

    public static final String PARENT_COMMENT_ID = "parent_comment_id";

    public static final String USER_ID = "user_id";

    public static final String FLOOR_COMMENT_ID = "floor_comment_id";

    public static final String PARENT_USER_ID = "parent_user_id";

    public static final String LIKE_COUNT = "like_count";

    public static final String COMMENT_CONTENT = "comment_content";

    public static final String COMMENT_INFO = "comment_info";

}
