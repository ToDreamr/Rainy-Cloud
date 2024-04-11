package com.pray.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pray.entity.po.Account;
import org.springframework.stereotype.Repository;

/**
* @author Rainy-Heights
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-08-24 13:00:41
* @Entity com.pray.entity.po.User
*/
@Repository
public interface UserMapper extends BaseMapper<Account> {

}




