package org.hcbm.platform;

import org.hzero.autoconfigure.platform.EnableHZeroPlatform;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroPlatform
@EnableEurekaClient
@SpringBootApplication
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}


