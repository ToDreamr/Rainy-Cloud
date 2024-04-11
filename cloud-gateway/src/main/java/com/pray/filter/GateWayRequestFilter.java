package com.pray.filter;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GateWayRequestFilter
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/22 0:56
 */
@Component
@Order(-1)//越小越高
@Slf4j
public class GateWayRequestFilter extends AbstractGatewayFilterFactory<Object> implements GlobalFilter {
    //路由白名单
    private List<String> whiteUrl;

    public void setWhiteUrl(List<String> whiteUrl) {
        this.whiteUrl = whiteUrl;
    }
    private static final String[] urls={"/service/borrow","/redis/borrow/borrowUsers","/service/userlist"};
    @PostConstruct
    private void webAllowedUrl(){
        ArrayList<String> whiteUrls = new ArrayList<>(Arrays.asList(urls));
        this.setWhiteUrl(whiteUrls);
    }

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
}
