package com.pray.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.logging.Log;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * MybatisConfig
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/25 12:36
 */
@Configuration
public class MybatisConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        long now = System.currentTimeMillis();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        Log logger = paginationInnerInterceptor.getLogger();
        long end = System.currentTimeMillis();
        logger.debug("连接查询耗时："+(end-now));
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

    //消费者调用实例，Spring没有自动注入，需要自己添加这个实例
    @Bean
    @LoadBalanced  //标记拦截要被Ribbon拦截,通过拦截器Interceptor来实现拦截
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
