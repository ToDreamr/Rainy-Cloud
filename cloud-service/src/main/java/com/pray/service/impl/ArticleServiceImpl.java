package com.pray.service.impl;

import com.pray.entity.blog.Article;
import com.pray.mapper.ArticleMapper;
import com.pray.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
