package com.pray.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pray.entity.po.Account;
import com.pray.feign.ServiceClient;
import com.pray.mapper.UserMapper;
import com.pray.service.UserService;
import com.pray.constants.PrayConstants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/24 11:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Account> implements UserService {
    //便于测试，目前只通过数据库查数据
    @Resource
    UserMapper userMapper;
    @Resource
    ServiceClient serviceClient;
    @Resource
    RedisTemplate<String,List<Account>> accountRedisTemplate;
    private  static final ObjectMapper mapper=new ObjectMapper();
    @Override
    public List<Account> getUserList() {
        String userKey = PrayConstants.USER_KEY;
        //Account account = (Account) serviceClient.CacheValue(userKey);//只获取缓存值
        List<Account> account= accountRedisTemplate.opsForValue().get(userKey);//只获取缓存值

        if (account ==null){
            try {
                LambdaQueryWrapper<Account> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.select();
                List<Account> accounts = userMapper.selectList(queryWrapper);
                accountRedisTemplate.opsForValue().set(userKey,accounts);
            }catch (Exception exception){
              log.error("写入缓存失败："+exception.getMessage());
            }
        }
        return account;
    }
}
