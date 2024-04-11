package com.pray.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pray.entity.po.Role;
import com.pray.feign.ServiceClient;
import com.pray.mapper.RoleMapper;
import com.pray.service.RoleService;
import com.pray.constants.PrayConstants;
import com.pray.utils.cache.RedisCacheUpdate;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
* @author Rainy-Heights
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-08-24 13:00:41
*/
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Resource
    RestTemplate restTemplate;
    private static final ObjectMapper mapper=new ObjectMapper();
    private final ExecutorService executorService= Executors.newFixedThreadPool(10);

    @Value("${server.port}")
    private String port;
    @Resource
    ServiceClient serviceClient;

    //实现Redis缓存热点数据：
    @SentinelResource(value = "listRole_cache",blockHandler = "replaceScheme"
            ,fallback = "fallBack",exceptionsToIgnore = IOException.class) //针对方法进行精准的限流，在IOException时不使用替代方案
    @Override
    public List listRole(int current, int pageSize) {
        //List<Role> listRole = (List<Role>) redisTemplate.opsForValue().get(key);
        //采用简单拼接url的方式来请求数据
        //List<Role> listRole=restTemplate.getForObject("http://localhost:8066/cache/role/list?key="+key,List.class);
        //读取之前检查：在从Redis中读取数据之前，先检查数据库中是否存在该数据。
        //如果数据库中不存在该数据，那么再从数据库中读取数据并存储到Redis中。这样可以确保Redis中的数据是最新的。
        log.info("检测到端口为{}的虚拟机{}查询角色信息",port,Thread.currentThread().getName());
        String key = PrayConstants.REDIS_CACHE + "listRole:"+current+":"+pageSize;
        List<Role> listRole = (List<Role>) serviceClient.CacheValue(key);
        return getList(current, pageSize, key, listRole);
    }

    //限流时的替代方案
    public List replaceScheme(int current, int pageSize, BlockException blockException) {
        return new ArrayList<Role>();
    }

    //默认替代方案
    public String fallBack(Throwable throwable) {
        return "默认替代方案的返回信息为："+throwable.getMessage();
    }
    private List<Role> getList(int current, int pageSize, String key, List<Role> listRole) {
        if (listRole == null) {
            Page<Role> page = getBaseMapper().selectPage(new Page<>(current, pageSize),
                    new LambdaQueryWrapper<Role>().orderByAsc(Role::getRoleCount));
            //注意：不要频繁的序列化和反序列化，可以直接返回
            //序列化查询到的数据
            //String jsonValue = mapper.writeValueAsString(page.getRecords());
            redisTemplate.opsForValue().set(key,
                    page.getRecords(),
                    2L,
                    TimeUnit.MINUTES);
            //反序列化查询的数据：
            //String redisJson = (String) redisTemplate.opsForValue().get(key);
            List<Role> res = (List<Role>) redisTemplate.opsForValue().get(key);
            return res;
        }
        return listRole;
    }

    /**
     * 更新缓存同时将数据同步到数据库里面
     * @param role
     * @return
     */
    @Override
    public String updatePageWithMutex(Role role) throws InterruptedException {
        List<Role> oldRoles = listRole(1, 10);
        String key = PrayConstants.REDIS_CACHE + "listRole:"+1+":"+10;
        Boolean delete = redisTemplate.delete(key);
        if (Boolean.FALSE.equals(delete)){
            return "缓存删除错误,请重试";
        }else {
            //先更新缓存再更新数据库
            oldRoles.add(role);
            RedisCacheUpdate cacheUpdate=new RedisCacheUpdate(key,redisTemplate,oldRoles);;//往缓存中更新数据库
            BlockingQueue<RedisCacheUpdate> queue=new ArrayBlockingQueue<>(10);
            do {
                queue.put(cacheUpdate);
            } while (queue.size() != 3);
            //更新策略：将更新消息发到消息队列，异步更新；定时同步更新，将更新操作放到定时器里面
            executorService.submit(queue.take());
            save(role);
            return "更新数据成功";
        }
    }
}




