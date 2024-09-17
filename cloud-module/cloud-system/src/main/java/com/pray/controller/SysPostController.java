package com.pray.controller;

import com.pray.annotation.RequiresPermissions;
import com.pray.constants.UserConstants;
import com.pray.domain.SysPost;
import com.pray.entity.Result;
import com.pray.poi.ExcelUtil;
import com.pray.service.ISysPostService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pray.entity.Result.success;
import static com.pray.entity.Result.convertResult;

/**
 * 岗位信息操作处理
 *
 * @author 春江花朝秋月夜
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController {
    @Resource
    private ISysPostService postService;


    @RequiresPermissions("system:post:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPost post) {
        List<SysPost> list = postService.selectPostList(post);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        util.exportExcel(response, list, "岗位数据");
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @RequiresPermissions("system:post:query")
    @GetMapping(value = "/{postId}")
    public Result getInfo(@PathVariable Long postId) {
        return success(postService.selectPostById(postId));
    }

    /**
     * 新增岗位
     */
    @RequiresPermissions("system:post:add")
    @PostMapping
    public Result add(@Validated @RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return Result.fail("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return Result.fail("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setCreateBy("默认用户");
        return convertResult(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:post:edit")
    @PutMapping
    public Result edit(@Validated @RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return Result.fail("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return Result.fail("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setUpdateBy("默认用户");
        return convertResult(postService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @RequiresPermissions("system:post:remove")
    @DeleteMapping("/{postIds}")
    public Result remove(@PathVariable Long[] postIds) {
        return convertResult(postService.deletePostByIds(postIds));
    }

    /**
     * 获取岗位选择框列表
     */
    @GetMapping("/optionselect")
    public Result optionselect() {
        List<SysPost> posts = postService.selectPostAll();
        return success(posts);
    }
}
