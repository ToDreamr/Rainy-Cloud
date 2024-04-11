package com.pray.entity.dto;

import com.pray.common.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * UserDto
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements BaseData {
    public String username;
    public int id;
}
