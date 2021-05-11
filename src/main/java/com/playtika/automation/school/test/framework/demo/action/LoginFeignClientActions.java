package com.playtika.automation.school.test.framework.demo.action;

import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.demo.client.LoginFeignClient;
import com.playtika.automation.school.test.framework.demo.pojo.RegistrationRequest;
import com.playtika.automation.school.test.framework.demo.pojo.RegistrationResponse;
import com.playtika.automation.school.test.framework.demo.pojo.User;

@AllArgsConstructor
public class LoginFeignClientActions {

    private final LoginFeignClient loginFeignClient;

    public RegistrationResponse registerUserAccount(User user) {
        RegistrationRequest request = new RegistrationRequest(user);
        return loginFeignClient.accountRegistration(request);
    }

}
