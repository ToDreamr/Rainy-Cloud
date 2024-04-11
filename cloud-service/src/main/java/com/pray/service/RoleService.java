package com.pray.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pray.entity.po.Role;

import java.util.List;

/**
* @author Rainy-Heights
* @description 针对表【role】的数据库操作Service
* @createDate 2023-08-24 13:00:41
*/

public interface RoleService extends IService<Role> {
    List<Role> listRole(int current, int pageSize);

    String updatePageWithMutex(Role role) throws JsonProcessingException, InterruptedException;
}
