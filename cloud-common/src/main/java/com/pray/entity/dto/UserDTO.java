package com.pray.entity.dto;

import com.pray.common.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * UserDto  用户基本信息
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends CommonDTO implements BaseData {

    public String username;

}
