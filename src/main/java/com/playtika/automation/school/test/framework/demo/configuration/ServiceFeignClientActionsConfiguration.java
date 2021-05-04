package com.playtika.automation.school.test.framework.demo.configuration;

import org.springframework.context.annotation.Bean;

import com.playtika.automation.school.test.framework.demo.action.ServiceFeignClientActions;
import com.playtika.automation.school.test.framework.demo.client.ServiceFeignClient;

public class ServiceFeignClientActionsConfiguration {

    @Bean
    public ServiceFeignClientActions serviceFeignClientActions(ServiceFeignClient client) {
        return new ServiceFeignClientActions(client);
    }

}
