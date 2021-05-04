package com.playtika.automation.school.test.framework.demo.configuration;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;

public class ServiceFeignClientConfiguration {

    @Bean
    public OkHttpClient feignClient() {
        return new OkHttpClient();
    }
    //
    //    @Bean
    //    public Contract feignContract() {
    //        return new Contract.Default();
    //    }

}
