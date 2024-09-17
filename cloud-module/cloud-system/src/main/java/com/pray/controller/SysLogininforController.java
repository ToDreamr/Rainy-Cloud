package com.pray.controller;


import com.pray.annotation.RequiresPermissions;
import com.pray.config.RedisService;
import com.pray.constants.CacheConstants;
import com.pray.entity.Result;
import com.pray.entity.sys.SysLogininfor;
import com.pray.poi.ExcelUtil;
import com.pray.service.ISysLogininforService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pray.entity.Result.success;
import static com.pray.entity.Result.toAjax;

/**
 * 系统访问记录
 *
 * @author 春江花朝秋月夜
 */
@RestController
@RequestMapping("/system/monitor/logininfor")
public class SysLogininforController extends BaseController {
    @Resource
    private ISysLogininforService logininforService;

    @Resource
    private RedisService redisService;


    @RequiresPermissions("monitor:logininfor:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLogininfor logininfor) {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        util.exportExcel(response, list, "登录日志");
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @DeleteMapping("/{infoIds}")
    public Result remove(@PathVariable Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @DeleteMapping("/clean")
    public Result clean() {
        logininforService.cleanLogininfor();
        return success();
    }

    @RequiresPermissions("onitor:logininfor:unlock")
    @GetMapping("/unlock/{userName}")
    public Result unlock(@PathVariable("userName") String userName) {
        redisService.deleteObject(CacheConstants.PWD_ERR_CNT_KEY + userName);
        return success();
    }

    /**
     * 插入访问记录-内部微服务调用
     * @param logininfor
     * @return
     */
    @PostMapping
    public Result add(@RequestBody SysLogininfor logininfor)
    {
        return toAjax(logininforService.insertLogininfor(logininfor));
    }
}
