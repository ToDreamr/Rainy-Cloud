# 基于SpringCloud+Nacos+Feign+Seata+RabbitMq+GateWay的图书分布式微服务事务调度项目

### 如你所见，这个项目所用的技术栈是以Alibaba的Nacos服务注册中心和分布式事务管理Seata构建。

本项目由于前期作为学习阶段，代码较为粗糙，整体架构如下：

![img.png](https://cdn.jsdelivr.net/gh/Todreamr/img-cloud/img/202404111913438.png)

##### Cloud-Service作为核心服务，调用Cloud-Core使用其缓存和借阅消息异步确认等服务。

使用了Seata+RabbitMq作为图书借阅事务处理方案，同时确认消费信息，防止消息重复消费。

GateWay统一Api接入层，配合Nginx作负载均衡，本项目的RPC服务调用采用Open-Feign开源项目
一并接入负载均衡。

#####  GateWay拦截器：
```java
@Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //设置一些东西
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获取请求头的信息
        List<String> authorization = request.getHeaders().get("Authorization");
        if (authorization != null && "stranger".equals(authorization.get(0))) {
            for (String item : whiteUrl) {
                if (item.equals(request.getURI().getPath())) {
                    log.info("<----------------------- 陌生请求且但请求路径在白名单内：{} ----------------------->",request.getURI().getPath());
                    return chain.filter(exchange);//放行
                }
            }
            response.setStatusCode(HttpStatusCode.valueOf(401));
            //不通过，直接拒绝请求
            log.info("<----------------------- 陌生请求且请求路径不在白名单内：{} ----------------------->",request.getURI().toString());
            return exchange.getResponse().setComplete();
        }
        log.info("<----------------------- 携带请求头参数：{} ----------------------->",authorization);
        return chain.filter(exchange);//放行
    }

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            log.info("<----------------------- 进入后台后端拦截器 ----------------------->");
            ServerHttpRequest request = exchange.getRequest();
            // 获取请求的url
            String url = request.getURI().toString();
            log.info("<----------------------- 请求的url：{} ----------------------->", url);
            //放行请求
            return chain.filter(exchange);
        });
    }
```

单点登录部分使用Security框架：

```java
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(conf->conf
                        .requestMatchers("/**").permitAll()
                )
                .formLogin(conf -> conf
                        .loginProcessingUrl("/auth/login").permitAll()
                        .successHandler(new LoginSuccessHandler(accountService,jwtUtils,stringRedisTemplate))
                        .failureHandler(new LoginFailureHandler())
                )
                //配置记住我
                .rememberMe(conf->conf
                        .rememberMeParameter("rememberMe")
                        .tokenRepository(persistentTokenRepository)
                        .tokenValiditySeconds(PrayConstants.USER_REMEMBER_SEC)//记住我的时间
                )
                .addFilterBefore(prayCorsFilter, CorsFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)//关闭跨域漏洞防御配置
                .logout(conf->conf
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler(new LogOutHandler(redisTemplate,jwtUtils)))
                .exceptionHandling(conf->conf.accessDeniedHandler((request, response, accessDeniedException)
                        -> System.out.println("当前异常的登录信息："+accessDeniedException)))
                .build();
    }
```

使用全局异常处理：CloudServiceException
```java
public class DefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(CloudServiceException.class)
    public ResponseEntity<Result<Object>> cloudExceptionHandler(CloudServiceException e) {
        logger.error("cloudExceptionHandler", e);

        int responseEnum = e.getCode();
        // 失败返回失败消息 + 状态码
        if (responseEnum != HttpStatus.OK.value()) {
            return ResponseEntity.status(HttpStatus.OK).
                    body(Result.
                            fail(responseEnum, e.getMessage()));
        }
        // 失败返回消息 状态码固定为直接显示消息的状态码
        return ResponseEntity.status(HttpStatus.OK).
                body(Result.fail(e.getMessage()));
    }

}
```

Feign模块：
```java
@FeignClient(value = "cloudcore",configuration = DefaultFeignConfig.class,fallback = ServerClientFallback.class )
public interface ServiceClient {
    /**
     * 获取缓存值
     * @param key
     * @return
     */
    @GetMapping(value = "/redis/cache/role/cacheValue")
    Object CacheValue(@RequestParam(value = "key") String key);
    /**
     * 设置缓存值
     * @param key 缓存键
     * @param cacheData 缓存数据
     * @return 是否写入成功
     */
    @PostMapping(value = "/redis/cache/role/setCacheValue")
    Boolean CacheValue(@RequestParam(value = "key") String key, @RequestParam(value = "cacheData") String cacheData);

    @GetMapping(value = "/redis/borrow/borrowUsers")
    List<Map<String,Object>> getBorrowUsers();

    @PostMapping("/redis/borrow/insertBorrow/{userId}/{bookId}")
    int insertBorrowRecord(@PathVariable(value = "userId") int userId, @PathVariable(value = "bookId") int bookId);

    @GetMapping("/redis/borrow/borrowDetails/{userId}/{bookId}")
    List<Map<String, Object>> selectBorrowDetails(@PathVariable(value = "userId") int userId, @PathVariable(value = "bookId") int bookId);
}
```
整体项目结构：
```tree
+---cloud-common
|   \---src
|       \---main
|           +---java
|           |   \---com
|           |       \---pray
|           |           +---common
|           |           +---constants
|           |           +---entity
|           |           |   +---dto
|           |           |   +---po
|           |           |   \---vo
|           |           |       +---request
|           |           |       \---response
|           |           +---exception
|           |           +---handler
|           |           \---utils
|           |               \---cache
+---cloud-core
|   \---src
|       +---main
|       |   +---java
|       |   |   \---com
|       |   |       \---pray
|       |   |           +---config
|       |   |           +---controller
|       |   |           +---service
|       |   |           |   \---impl
|       |   |           \---utils
|       |   |               \---impl
|       |   \---resources
+---cloud-feign
|   \---src
|       +---main
|       |   +---java
|       |   |   \---com
|       |   |       \---pray
|       |   |           +---config
|       |   |           \---feign
|       |   |               \---api
|       |   \---resources
|       \---test
|           \---java
+---cloud-gateway
|   \---src
|       \---main
|           +---java
|           |   \---com
|           |       \---pray
|           |           +---config
|           |           \---filter
|           \---resources
+---cloud-mapper
|   \---src
|       +---main
|       |   +---java
|       |   |   \---com
|       |   |       \---pray
|       |   |           \---mapper
|       |   \---resources
|       |       \---mapper
+---cloud-oauth2-sso
|   \---src
|       +---main
|       |   +---java
|       |   |   \---com
|       |   |       \---pray
|       |   |           \---config
|       |   \---resources
+---cloud-service
|   \---src
|       +---main
|       |   +---java
|       |   |   \---com
|       |   |       \---pray
|       |   |           +---aop
|       |   |           +---config
|       |   |           +---controller
|       |   |           \---service
|       |   |               \---impl
|       |   \---resources
+---doc
|   \---img
|   \---doc_tree.txt


```
