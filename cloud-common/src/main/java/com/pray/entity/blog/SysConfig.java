package com.pray.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
@TableName("sys_config")
public class SysConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String configName;

    /**
     * 键名
     */
    private String configKey;

    /**
     * 键值
     */
    private String configValue;

    /**
     * 1 私用 2 公开
     */
    private String configType;


    public static final String CONFIG_NAME = "config_name";

    public static final String CONFIG_KEY = "config_key";

    public static final String CONFIG_VALUE = "config_value";

    public static final String CONFIG_TYPE = "config_type";

}
