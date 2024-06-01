package com.pray.entity.blog;

import com.pray.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 资源信息
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Getter
@Setter
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源路径
     */
    private String path;

    /**
     * 资源内容的大小，单位：字节
     */
    private Integer size;

    /**
     * 文件名称
     */
    private String originalName;

    /**
     * 资源的 MIME 类型
     */
    private String mimeType;

    /**
     * 是否启用[0:否，1:是]
     */
    private Boolean status;

    /**
     * 存储平台
     */
    private String storeType;


    public static final String USER_ID = "user_id";

    public static final String TYPE = "type";

    public static final String PATH = "path";

    public static final String SIZE = "size";

    public static final String ORIGINAL_NAME = "original_name";

    public static final String MIME_TYPE = "mime_type";

    public static final String STATUS = "status";

    public static final String STORE_TYPE = "store_type";

}
