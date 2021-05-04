package com.playtika.automation.school.test.framework.demo.configuration;

import org.springframework.context.annotation.Bean;

import com.playtika.automation.school.test.framework.demo.pojo.User;

public class UserConfiguration {

    @Bean
    public User user() {
        return new User();
    }
}
