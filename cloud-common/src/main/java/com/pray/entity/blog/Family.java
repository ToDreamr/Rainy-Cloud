package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 家庭信息
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("family")
public class Family extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 背景封面
     */
    private String bgCover;

    /**
     * 男生头像
     */
    private String manCover;

    /**
     * 女生头像
     */
    private String womanCover;

    /**
     * 男生昵称
     */
    private String manName;

    /**
     * 女生昵称
     */
    private String womanName;

    /**
     * 计时
     */
    private String timing;

    /**
     * 倒计时标题
     */
    private String countdownTitle;

    /**
     * 倒计时时间
     */
    private String countdownTime;

    /**
     * 是否启用[0:否，1:是]
     */
    private Boolean status;

    /**
     * 额外信息
     */
    private String familyInfo;

    /**
     * 点赞数
     */
    private Integer likeCount;


    public static final String USER_ID = "user_id";

    public static final String BG_COVER = "bg_cover";

    public static final String MAN_COVER = "man_cover";

    public static final String WOMAN_COVER = "woman_cover";

    public static final String MAN_NAME = "man_name";

    public static final String WOMAN_NAME = "woman_name";

    public static final String TIMING = "timing";

    public static final String COUNTDOWN_TITLE = "countdown_title";

    public static final String COUNTDOWN_TIME = "countdown_time";

    public static final String STATUS = "status";

    public static final String FAMILY_INFO = "family_info";

    public static final String LIKE_COUNT = "like_count";

}
