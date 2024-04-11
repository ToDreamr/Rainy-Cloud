package com.pray;

//import com.pray.config.GateWayGlobalConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
//@LoadBalancerClients(defaultConfiguration = GateWayGlobalConfig.class)
public class CloudGateWayApplication {

    public static void main(String[] args) {
         SpringApplication.run(CloudGateWayApplication.class,args);
    }
}
