package com.pray.entity.blog;

import com.pray.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
public class Sort extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String sortName;

    /**
     * 分类描述
     */
    private String sortDescription;

    /**
     * 分类类型[0:导航栏分类，1:普通分类]
     */
    private Integer sortType;

    /**
     * 分类优先级：数字小的在前面
     */
    private Integer priority;


    public static final String SORT_NAME = "sort_name";

    public static final String SORT_DESCRIPTION = "sort_description";

    public static final String SORT_TYPE = "sort_type";

    public static final String PRIORITY = "priority";

}
