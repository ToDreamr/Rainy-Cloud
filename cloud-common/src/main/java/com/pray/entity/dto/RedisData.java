package com.pray.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * RedisData
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/9/14 17:23
 */
@Data
public class RedisData {
    //不采用继承，因为具有侵入性，改变了原有的逻辑
    private LocalDateTime expireTime;//设置过期时间
    private Object data;//插入的数据对象，存储数据对象
}
