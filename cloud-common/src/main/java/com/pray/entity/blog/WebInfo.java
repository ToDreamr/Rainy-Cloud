package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 网站信息表
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("web_info")
public class WebInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 网站名称
     */
    private String webName;

    /**
     * 网站信息
     */
    private String webTitle;

    /**
     * 公告
     */
    private String notices;

    /**
     * 页脚
     */
    private String footer;

    /**
     * 背景
     */
    private String backgroundImage;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 随机头像
     */
    private String randomAvatar;

    /**
     * 随机名称
     */
    private String randomName;

    /**
     * 随机封面
     */
    private String randomCover;

    /**
     * 看板娘消息
     */
    private String waifuJson;

    /**
     * 是否启用[0:否，1:是]
     */
    private Boolean status;


    public static final String WEB_NAME = "web_name";

    public static final String WEB_TITLE = "web_title";

    public static final String NOTICES = "notices";

    public static final String FOOTER = "footer";

    public static final String BACKGROUND_IMAGE = "background_image";

    public static final String AVATAR = "avatar";

    public static final String RANDOM_AVATAR = "random_avatar";

    public static final String RANDOM_NAME = "random_name";

    public static final String RANDOM_COVER = "random_cover";

    public static final String WAIFU_JSON = "waifu_json";

    public static final String STATUS = "status";

}
