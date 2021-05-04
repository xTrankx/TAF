package com.playtika.automation.school.test.framework.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.playtika.automation.school.test.framework.demo.configuration.ServiceFeignClientConfiguration;
import com.playtika.automation.school.test.framework.demo.pojo.RegistrationRequest;
import com.playtika.automation.school.test.framework.demo.pojo.RegistrationResponse;

@FeignClient(
        contextId = "loginClientId",
        name = "login-client",
        url = "${host.baseurl}",
        path = "/v1",
        configuration = ServiceFeignClientConfiguration.class
)
public interface ServiceFeignClient {
    @PostMapping(path = "/accounts", consumes = "application/json")
    RegistrationResponse accountRegistration(@RequestBody RegistrationRequest request);
}
