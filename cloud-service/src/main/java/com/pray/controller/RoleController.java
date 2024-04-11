package com.pray.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pray.aop.SocketServer;
import com.pray.entity.po.Role;
import com.pray.service.RoleService;
import com.pray.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController
 *
 * @author 春江花朝秋月夜
 * @since 2023/11/12 14:57
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    RoleService roleService;
    @Value("${dateconfig.time}")
    private String time;
    @RequestMapping(path = "/time")
    public String time(){
        return time;
    }

    /**
     * 角色信息
     * @return
     */
    @GetMapping()
   // @SentinelResource("getRoleList")   //监控此方法，无论被谁执行都在监控范围内，这里给的value是自定义名称，这个注解可以加在任何方法上，包括Controller中的请求映射方法，跟HystrixCommand贼像
    public Result<List<Role>> geUserList()  {
        return  Result.ok(roleService.listRole(1,10),"查询成功");
    }
    @PostMapping("/update")
    public Result<String> updatePageInfo(@RequestBody Role role) throws JsonProcessingException, InterruptedException {
        //返回插入的更新数据
        return Result.ok(roleService.updatePageWithMutex(role));
    }
    @GetMapping("/copy")  //测试sentinel链路规则，只有指定路由下面的链路关联正确时才会触发限流
    public Result<List<Role>> geUserList2()  {
        return  Result.ok(roleService.listRole(1,10),"查询成功");
    }

}
