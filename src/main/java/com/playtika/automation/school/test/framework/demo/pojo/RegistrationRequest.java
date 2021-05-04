package com.playtika.automation.school.test.framework.demo.pojo;

import lombok.Data;

@Data
public class RegistrationRequest {

    String email;
    String password;

    public RegistrationRequest(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
