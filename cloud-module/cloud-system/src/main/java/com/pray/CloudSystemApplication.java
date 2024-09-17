package com.pray;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 春江花朝秋月夜
 * @description 系统模块
 * @create 2024-04-24-15:29
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.pray.mapper")
public class CloudSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudSystemApplication.class, args);
    }
}