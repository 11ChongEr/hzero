package org.hcbm.oauth;

import org.hzero.autoconfigure.oauth.EnableHZeroOauth;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroOauth
@EnableEurekaClient
@SpringBootApplication
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}


