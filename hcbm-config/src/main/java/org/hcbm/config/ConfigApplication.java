package org.hcbm.config;

import org.hzero.autoconfigure.config.EnableHZeroConfig;
import io.choerodon.eureka.event.EurekaEventHandler;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.config.server.config.ConfigServerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroConfig
@SpringBootApplication(exclude = {ConfigServerAutoConfiguration.class})
@EnableEurekaClient
public class ConfigApplication {

    public static void main(String[] args) {
        EurekaEventHandler.getInstance().init();
        SpringApplication.run(ConfigApplication.class, args);
    }
}


