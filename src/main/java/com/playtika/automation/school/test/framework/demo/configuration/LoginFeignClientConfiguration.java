package com.playtika.automation.school.test.framework.demo.configuration;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;

public class LoginFeignClientConfiguration {

    @Bean
    public OkHttpClient feignClient() {
        return new OkHttpClient();
    }
}
