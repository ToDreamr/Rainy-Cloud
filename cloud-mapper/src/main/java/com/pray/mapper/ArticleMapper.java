package com.pray.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pray.entity.blog.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    @Update("update article set view_count=view_count+1 where id=#{id}")
    int updateViewCount(@Param("id") Integer id);
}
