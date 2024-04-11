package com.pray;

//import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Rainy-Heights
 */
//@EnableAutoDataSourceProxy
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.pray.feign")//自动装配的开关，实现了Aware接口,同时根据包路径注入Feign调用服务
@EnableAsync//测试异步任务
@MapperScan(basePackages = "com.pray.mapper")
@SpringBootApplication
public class CloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceApplication.class, args);
    }
}
