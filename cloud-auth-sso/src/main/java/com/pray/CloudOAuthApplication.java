package com.pray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CloudOAuthApplication
 *
 * @author 春江花朝秋月夜
 * @since 2024/2/27 18:12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudOAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudOAuthApplication.class);
    }
}
