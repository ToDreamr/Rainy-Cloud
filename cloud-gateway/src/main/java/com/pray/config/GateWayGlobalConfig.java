//package com.pray.config;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
//import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledGlobalFilter;
//import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
//import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
//import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
//import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
///**
// * GateWayGlobalConfig
// * GateWay过滤器（全局过滤器和网关过滤器）
// * @author 春江花朝秋月夜
// * @since 2024/1/26 17:44
// */
//@Configuration
//public class GateWayGlobalConfig {
//    //自动配置：GatewayReactiveLoadBalancerClientAutoConfiguration
//    //这是一个全局过滤器
//    @Bean
//    @ConditionalOnBean(LoadBalancerClientFactory.class)
//    @ConditionalOnMissingBean(ReactiveLoadBalancerClientFilter.class)
//    @ConditionalOnEnabledGlobalFilter
//    public ReactiveLoadBalancerClientFilter gatewayLoadBalancerClientFilter(LoadBalancerClientFactory clientFactory,
//                                                                            GatewayLoadBalancerProperties properties) {
//        return new ReactiveLoadBalancerClientFilter(clientFactory, properties);
//    }
//
//    @Bean
//    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
//                                                                                   LoadBalancerClientFactory loadBalancerClientFactory) {
//        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);//实例名
//        return new GrayLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);//参数:提供实例，实例名
//        //通过修改负载均衡过滤器实现
//    }
//}
