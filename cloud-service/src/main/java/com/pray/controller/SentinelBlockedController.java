package com.pray.controller;

import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.pray.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SentinelBlockedController
 * 断路器页面
 * @author 春江花朝秋月夜
 * @since 2024/2/19 14:00
 */
@RestController
@RequestMapping(path = "/block")
public class SentinelBlockedController {
    @GetMapping
    public Result<String> blocked(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("code",403);
        jsonObject.addProperty("success",false);
        jsonObject.addProperty("message","Interface require too many,try again later!");
        return Result.ok(jsonObject.getAsString());
    }
}
