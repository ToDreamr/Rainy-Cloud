package com.pray.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PreviousUserController
 *
 * @author 春江花朝秋月夜
 * @since 2023/11/12 14:57
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Value("${dateconfig.time}")
    private String time;
    @RequestMapping(path = "/time")
    public String time(){
        return time;
    }

}
