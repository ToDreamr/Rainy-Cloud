package com.pray.entity.blog;

import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 标签
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
public class Label extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    private Integer sortId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签描述
     */
    private String labelDescription;


    public static final String SORT_ID = "sort_id";

    public static final String LABEL_NAME = "label_name";

    public static final String LABEL_DESCRIPTION = "label_description";

}
