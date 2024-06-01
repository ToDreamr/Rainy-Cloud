package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("article")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 分类id
     */
    private Integer sortId;

    /**
     * 文章封面
     */
    private String articleCover;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 是否可见[0:否，1:是]
     */
    private Boolean viewStatus;

    /**
     * 文章密码
     */
    private String password;

    /**
     * 提示
     */
    private String tips;

    /**
     * 是否推荐[0:否，1:是]
     */
    private Boolean recommendStatus;

    /**
     * 是否启用评论[0:否，1:是]
     */
    private Boolean commentStatus;


    public static final String USER_ID = "user_id";

    public static final String SORT_ID = "sort_id";

    public static final String ARTICLE_COVER = "article_cover";

    public static final String ARTICLE_TITLE = "article_title";

    public static final String ARTICLE_CONTENT = "article_content";

    public static final String VIEW_COUNT = "view_count";

    public static final String VIEW_STATUS = "view_status";

    public static final String PASSWORD = "password";

    public static final String TIPS = "tips";

    public static final String RECOMMEND_STATUS = "recommend_status";

    public static final String COMMENT_STATUS = "comment_status";

}
