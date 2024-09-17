package com.pray.controller;

import com.pray.config.RainyConfig;
import com.pray.utils.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author 春江花朝秋月夜
 */
@RestController("/system/dashboard")
public class SysIndexController {
    /**
     * 系统基础配置
     */
    @Resource
    private RainyConfig rainyConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用 Rainy-Cloud，当前版本：v{}，请通过前端地址访问。", rainyConfig.getName(), rainyConfig.getVersion());
    }
}
