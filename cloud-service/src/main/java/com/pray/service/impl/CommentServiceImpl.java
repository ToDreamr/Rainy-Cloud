package com.pray.service.impl;

import com.pray.entity.blog.Comment;
import com.pray.mapper.CommentMapper;
import com.pray.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章评论表 服务实现类
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
