package com.pray.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

/**
 * JwtTokenConfig
 *
 * @author 春江花朝秋月夜
 * @since 2024/2/29 19:20
 */
@Configuration
public class JwtTokenConfig {
    @Bean
    public JwtAuthenticationConverter tokenConverter(){
        //用于从JWT令牌中提取授权信息并将其转换为GrantedAuthority对象的集合
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        //JWT令牌中的授权信息在名为"authorities"的声明中
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        //授权信息中不包含前缀
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
