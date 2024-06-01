package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 树洞
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("tree_hole")
public class TreeHole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 留言
     */
    private String message;


    public static final String AVATAR = "avatar";

    public static final String MESSAGE = "message";

}
