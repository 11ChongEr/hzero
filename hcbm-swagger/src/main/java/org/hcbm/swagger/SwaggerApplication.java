package org.hcbm.swagger;

import org.hzero.autoconfigure.swagger.EnableHZeroSwagger;
import io.choerodon.eureka.event.EurekaEventHandler;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroSwagger
@EnableEurekaClient
@SpringBootApplication
public class SwaggerApplication {

    public static void main(String[] args) {
        EurekaEventHandler.getInstance().init();
        SpringApplication.run(SwaggerApplication.class, args);
    }
}


