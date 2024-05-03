package com.pray.mapper;

import com.pray.model.AuthAccount;
import feign.Param;
import org.springframework.stereotype.Repository;

/**
* @author Rainy-Heights
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-04-14 15:41:23
* @Entity com.pray.entity.AuthUser
*/
@Repository
public interface AuthUserMapper {
    AuthAccount getAuthInfoByUserName(@Param("username") String username);
}




