package com.pray.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pray.entity.blog.TreeHole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 树洞 Mapper 接口
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
public interface TreeHoleMapper extends BaseMapper<TreeHole> {
    List<TreeHole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);
}
