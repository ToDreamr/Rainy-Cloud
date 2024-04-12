package com.pray.controller;

import com.pray.entity.po.Role;
import com.pray.utils.RoleCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * RoleCacheController
 *
 * @author 春江花朝秋月夜
 * @since 2023/12/7 0:31
 */
@RestController
@RequestMapping("/cache/role")
public class RoleCacheController {
    @Autowired
    RoleCacheService roleCacheService;
    Logger logger= LoggerFactory.getLogger(RoleCacheController.class);
    @GetMapping("/list")
    public List<Role> listRoleCache(@RequestParam String key){
        logger.info("缓存查询：key:{}",key);
        if (roleCacheService.cacheValue(key)!=null){
            return Collections.singletonList((Role) roleCacheService.cacheValue(key));
        }
        return null;
    }
    @GetMapping("/cacheValue")
    public Object cacheValue(@RequestParam String key){
        logger.info("缓存查询：key:{}",key);
        return roleCacheService.cacheValue(key);
    }
    @PostMapping("/setCacheValue")
    public Boolean cacheValue(@RequestParam String key, @RequestParam String cacheData){
        logger.info("缓存查询：key:{}",key);
        return roleCacheService.setCacheValue(key,cacheData);
    }
}
