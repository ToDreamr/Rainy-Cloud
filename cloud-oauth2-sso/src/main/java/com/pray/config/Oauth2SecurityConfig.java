//package com.pray.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//
//import java.util.UUID;
//
///**
// * <p>
// * Oauth2SecurityConfig
// * <p>
// *
// * @author 春江花朝秋月夜
// * @since 2024/2/3 0:43
// */
//@Configuration
//public class Oauth2SecurityConfig{
//    @Bean
//    @Order(1)//指定执行优先级
//    public SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//
//        return http
//                //为 OAuth2 认证服务器添加 OIDC 支持
//                .getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults())
//                .and()
//                .exceptionHandling(e -> e
//                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
//                //当未经身份验证的用户尝试访问受保护的资源时，将用户重定向到Security的默认登录页面
//                .build();
//
//    }
//
//    @Bean
//    @Order(2)
//    //表单登录与身份验证的请求授权
//    public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .formLogin(Customizer.withDefaults())
//                .httpBasic()
//                .and()
//                //授权任何已认证的用户可以访问任何请求
//                .authorizeHttpRequests(authorize ->authorize.anyRequest().authenticated())
//                .build();
//
//    }
//
//     @Bean
//     PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }
//
//     @Bean
//     public AuthorizationServerSettings authorizationServerSettings() {
//         return AuthorizationServerSettings.builder().build();
//     }
//
//    //对Oauth2实现
//     @Bean
//     public RegisteredClientRepository registeredClientRepository() {
//         RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                 .clientId("client")
//                 .clientSecret(passwordEncoder().encode("secret"))//修改此处
//                 .scope("read")
//                 .scope(OidcScopes.OPENID)
//                 .scope(OidcScopes.PROFILE)
//                 .redirectUri("http://www.baidu.com")
//                 .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                 .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                 .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                 .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                 .authorizationGrantType(AuthorizationGrantType.JWT_BEARER)
//                 .build();
//         return new InMemoryRegisteredClientRepository(registeredClient);
//     }
//}
