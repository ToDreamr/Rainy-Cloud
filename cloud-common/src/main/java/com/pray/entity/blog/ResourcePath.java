package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 资源聚合
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("resource_path")
public class ResourcePath extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类
     */
    private String classify;

    /**
     * 封面
     */
    private String cover;

    /**
     * 链接
     */
    private String url;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 是否启用[0:否，1:是]
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;


    public static final String TITLE = "title";

    public static final String CLASSIFY = "classify";

    public static final String COVER = "cover";

    public static final String URL = "url";

    public static final String INTRODUCTION = "introduction";

    public static final String TYPE = "type";

    public static final String STATUS = "status";

    public static final String REMARK = "remark";

}
