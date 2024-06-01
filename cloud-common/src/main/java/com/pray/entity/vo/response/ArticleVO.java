package com.pray.entity.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pray.entity.blog.Label;
import com.pray.entity.blog.Sort;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * ArticleVO
 * 文章响应视图层对象
 * @author 春江花朝秋月夜
 * @since 2024/6/1 19:59
 */
@Data
public class ArticleVO {
    private Integer id;

    private Integer userId;

    //查询为空时，随机选择
    private String articleCover;

    @NotBlank(message = "文章标题不能为空")
    private String articleTitle;

    @NotBlank(message = "文章内容不能为空")
    private String articleContent;

    private Integer viewCount;

    private Integer likeCount;

    private Boolean commentStatus;

    private Boolean recommendStatus;

    private String videoUrl;

    private String password;

    private String tips;

    private Boolean viewStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String updateBy;

    @NotNull(message = "文章分类不能为空")
    private Integer sortId;

    @NotNull(message = "文章标签不能为空")
    private Integer labelId;

    // 需要查询封装
    private Integer commentCount;
    private String username;
    private Sort sort;
    private Label label;
    private Boolean hasVideo = false;
}
