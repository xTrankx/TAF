package com.playtika.automation.school.test.framework.demo.action;

import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.demo.client.ServiceFeignClient;
import com.playtika.automation.school.test.framework.demo.pojo.RegistrationRequest;
import com.playtika.automation.school.test.framework.demo.pojo.RegistrationResponse;
import com.playtika.automation.school.test.framework.demo.pojo.User;

@AllArgsConstructor
public class ServiceFeignClientActions {

    private final ServiceFeignClient serviceFeignClient;

    public RegistrationResponse registerUserAccount(User user) {
        RegistrationRequest request = new RegistrationRequest(user);
        return serviceFeignClient.accountRegistration(request);
    }

}
