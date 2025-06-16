package com.pray.cache;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * RedisPreProcess
 * 根据面试被问到的问题：生命周期，线程安全性，循环依赖
 *
 * @author 春江花朝秋月夜
 * @since 2024/7/12 15:21
 */
//@Component
@Slf4j
public class RedisPreProcess implements InitializingBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, BeanPostProcessor,DisposableBean {
    //缓存预热处理，通过预先调查用户热数据，先自查询一次，写入缓存中，避免冷启动每次查询都走DB给数据库带来巨大压力
    @Resource
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化Bean：使用缓存工具"+redisTemplate.toString()+"执行缓存预热");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.info("设置类加载器");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("设置类工厂");
    }

    @Override
    public void setBeanName(String name) {
        log.info("设置Bean名");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void destroy() throws Exception {
        log.info("死之前说句话");
    }
}
