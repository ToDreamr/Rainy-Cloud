package com.pray;

//import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * CloudRedisApplication
 *
 * @author 春江花朝秋月夜
 * @since 2023/12/7 0:26
 */
//@EnableAutoDataSourceProxy
@MapperScan(basePackages = "com.pray.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.pray.feign")//自动装配的开关，实现了Aware接口,同时根据包路径注入Feign调用服务
@EnableDiscoveryClient
public class CloudRedisApplication {
    //拆分查询缓存的微服务
    public static void main(String[] args) {
        SpringApplication.run(CloudRedisApplication.class,args);
    }
}
