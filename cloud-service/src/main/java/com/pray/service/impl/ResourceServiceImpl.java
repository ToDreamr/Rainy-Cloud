package com.pray.service.impl;

import com.pray.entity.blog.Resource;
import com.pray.mapper.ResourceMapper;
import com.pray.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源信息 服务实现类
 * </p>
 *
 * @author 春江花朝秋月夜
 * @since 2024-06-01
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
