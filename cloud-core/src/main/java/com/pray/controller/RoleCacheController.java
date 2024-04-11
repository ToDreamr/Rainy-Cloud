package com.pray.controller;

import com.pray.entity.po.Role;
import com.pray.utils.RoleCacheService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    RoleCacheService<Role> roleCacheService;
    Logger logger= LoggerFactory.getLogger(RoleCacheController.class);
    @GetMapping("/list")
    public List<Role> listRoleCache(@RequestParam String key){
        logger.info("缓存查询：key:{}",key);
        return roleCacheService.listRole(key);
    }
    @GetMapping("/cacheValue")
    public Object CacheValue(@RequestParam String key){
        logger.info("缓存查询：key:{}",key);
        return roleCacheService.cacheValue(key);
    }
    @PostMapping("/setCacheValue")
    public Boolean CacheValue(@RequestParam String key,@RequestParam String cacheData){
        logger.info("缓存查询：key:{}",key);
        return roleCacheService.setCacheValue(key,cacheData);
    }
}
