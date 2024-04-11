package com.pray.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * QiniuConfigProperties
 *
 * @author 春江花朝秋月夜
 * @since 2024/3/1 20:58
 */
@Configuration
@ConfigurationProperties(prefix = "qi-niu-oss")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiniuConfigProperties {

    private String accessKey;
    private String secretKey;
    private String hostName;
    private String zone;
    private String bucketName;
}
