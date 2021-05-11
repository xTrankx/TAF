package com.playtika.automation.school.test.framework.demo.configuration;

import java.util.List;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.playtika.automation.school.test.framework.demo.pojo.User;

public class AuthenticatedFeignClientConfiguration {

    @Value("${host.access-token-uri}")
    private String accessTokenUri;

    @Value("${host.client-id}")
    private String clientId;

    @Value("${host.client-secret}")
    private String clientSecret;

    @Value("${host.scope}")
    private List<String> scope;

    @Bean
    public RequestInterceptor requestInterceptor(User user) {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource(user));
    }

    private OAuth2ProtectedResourceDetails resource(User user) {
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        details.setAccessTokenUri(accessTokenUri);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setScope(scope);
        details.setUsername(user.getEmail());
        details.setPassword(user.getPassword());
        return details;
    }
}
