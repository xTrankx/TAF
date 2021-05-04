package com.playtika.automation.school.test.framework.demo.configuration;

import org.springframework.context.annotation.Bean;

import com.playtika.automation.school.test.framework.demo.action.AuthenticatedFeignClientActions;
import com.playtika.automation.school.test.framework.demo.client.AuthenticatedFeignClient;

public class AuthenticatedFeignClientActionsConfiguration {

    @Bean
    public AuthenticatedFeignClientActions authenticatedFeignClientActions(AuthenticatedFeignClient client) {
        return new AuthenticatedFeignClientActions(client);
    }
}
