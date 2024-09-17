package com.pray.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * BaseEntity
 *
 * @author 春江花朝秋月夜
 * @since 2024/6/1 19:31
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    public static final String ID = "id";

    public static final String UPDATE_BY = "update_by";

    public static final String CREATE_BY = "create_by";

    public static final String UPDATE_TIME = "update_time";

    public static final String CREATE_TIME = "create_time";

    public static final String DELETED = "deleted";

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 更新主体
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 创建主体
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 记录更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 逻辑删除标记
     */
    @TableLogic(value = "0", delval = "1")
    private Boolean deleted;


    /**
     * 备注
     */
    @TableField(exist = false)
    private String remark;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
    public String getRemark() {
        return remark;
    }

}
