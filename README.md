
# Rainy-Cloud  一套基于SpringCloud的的微服务智能项目管理系统。
<hr/>

> 简介：Rainy-Cloud包括认证、流程、项目管理、用户、网关等服务。包含了 Redis 缓存、RabbitMQ 消息队列、Spring Security 安全框架、Nacos 服务注册和发现、Sentinel 熔断限流、Seata 分布式事务、OpenFeign 服务调用，融合了现在大部分微服务/分布式项目的技术栈。

## 项目亮点

- **热门技术**：采用时下企业最热门的技术框架，如 SpringCloudAlibaba、Nacos、Open-Feign、Sentinel等。
- **独立认证**：使用Spring-Security自行封装了一套安全认证模块，包括过滤器，JWT-Token等；独立出单点登录服务作为系统统一权限认证中心。
- **统一网关**：使用Gateway作为统一请求网关，对白名单，请求日志，请求校验作了全方位的处理。
- **层级分明**：项目层级和模块清析分明，具体包括系统安全认证中心，系统网关，缓存服务中心，消息服务中心，系统服务中心，系统核心（常用的实体和工具类及上下文部分），各模块独立维护，简化代码编写。（~~拒绝屎山代码！！！~~）
- **适合初学者**：本项目使用简单的抽象逻辑，封装层级没有像某些项目那样花哨，希望大部分人可以从我作为探索者的角度去理解微服务项目需要哪些模块和功能，如何自己去考虑项目设计。


##### Cloud-Service作为核心服务，调用Cloud-Core使用其缓存和消息异步确认等服务。

使用了Seata+RabbitMQ作为事务处理方案，同时确认消费信息，防止消息重复消费。

GateWay统一Api接入层，配合Nginx作负载均衡，本项目的RPC服务调用采用Open-Feign开源项目
一并接入负载均衡。

#####  GateWay拦截器：
```java
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

单点登录部分使用Filter过滤器自定义封装了cloud-security和cloud-auth-sso模块：

全局注册认证过滤器
```java
    public FilterRegistrationBean<AuthFilter> filterRegistration(AuthFilter authFilter) {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        // 添加过滤器
        registration.setFilter(authFilter);
        // 设置过滤路径，/*所有路径
        log.info("<----------------------- 加入AuthFilter拦截路径：----------------------->");
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        // 设置优先级
        registration.setOrder(-120);
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        return registration;
    }
```

部分认证逻辑

```java
    public Result<AuthInfoInTokenBO> getAuthInfoByUserNameAndPassword(String inputUserName, String password) {
        //获取登录账户
        AuthAccount authAccount = authUserMapper.getAuthInfoByUserName(inputUserName);
        if (authAccount==null){
            throw new CloudServiceException("不存在这样的用户");
        }
        if (!passwordEncoder.matches(password,authAccount.getPassword())){
          throw new CloudServiceException("密码错误，异常的尝试");
        }
        //构建上下文保存登录对象
        AuthInfoInTokenBO tokenBO = new AuthInfoInTokenBO();
        AuthUser authUser = new AuthUser();

        authUser.setUserId((long) authAccount.getId());

        String accessToken = jwtUtil.createToken(authAccount.getUsername(),authAccount.getId());
        authUser.setAccessToken(accessToken);


        String refreshToken = jwtUtil.createRefreshToken();
        try {
            // TODO 完成刷新token
            tokenBO.setRefreshToken(refreshToken);
        } catch (Exception e) {
            throw new CloudServiceException("颁发或刷新Token异常");
        }
        tokenBO.setExpiresIn(3000);
        //授权登录用户
        tokenBO.setAuthUser(authUser);
        return Result.ok(tokenBO);
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

Feign模块部分接口：
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

