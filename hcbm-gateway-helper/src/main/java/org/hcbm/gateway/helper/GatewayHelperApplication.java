package org.hcbm.gateway.helper;

import org.hzero.autoconfigure.gateway.helper.EnableHZeroGatewayHelper;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroGatewayHelper
@EnableEurekaClient
@SpringBootApplication
public class GatewayHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayHelperApplication.class, args);
    }
}


