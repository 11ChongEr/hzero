package org.hcbm.iam;

import org.hzero.autoconfigure.iam.EnableHZeroIam;
import io.choerodon.eureka.event.EurekaEventHandler;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroIam
@EnableEurekaClient
@SpringBootApplication
public class IamApplication {

    public static void main(String[] args) {
        EurekaEventHandler.getInstance().init();
        SpringApplication.run(IamApplication.class, args);
    }
}


