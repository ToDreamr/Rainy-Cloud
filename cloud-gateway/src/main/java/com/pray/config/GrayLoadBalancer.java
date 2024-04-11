package com.pray.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import reactor.core.publisher.Mono;

/**
 * GrayLoadBalancer
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/28 1:28
 */
public class GrayLoadBalancer<ServiceInstanceListSupplier> implements ReactorLoadBalancer<ServiceInstanceListSupplier> {
    ObjectProvider<ServiceInstanceListSupplier> objectProvider;
    String instanceName;

    public GrayLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> objectProvider, String instanceName) {
        this.objectProvider = objectProvider;
        this.instanceName = instanceName;
    }

    //通过重写Choose来实现自定义
    @Override
    public Mono<Response<ServiceInstanceListSupplier>> choose(Request request) {
        ReactorLoadBalancer<ServiceInstanceListSupplier> loadBalancer = (ReactorLoadBalancer<ServiceInstanceListSupplier>) this.objectProvider.getObject();
        return loadBalancer.choose(request);
    }
}
