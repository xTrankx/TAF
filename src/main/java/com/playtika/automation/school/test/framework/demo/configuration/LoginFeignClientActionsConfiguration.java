package com.playtika.automation.school.test.framework.demo.configuration;

import org.springframework.context.annotation.Bean;

import com.playtika.automation.school.test.framework.demo.action.LoginFeignClientActions;
import com.playtika.automation.school.test.framework.demo.client.LoginFeignClient;

public class LoginFeignClientActionsConfiguration {

    @Bean
    public LoginFeignClientActions serviceFeignClientActions(LoginFeignClient client) {
        return new LoginFeignClientActions(client);
    }

}
