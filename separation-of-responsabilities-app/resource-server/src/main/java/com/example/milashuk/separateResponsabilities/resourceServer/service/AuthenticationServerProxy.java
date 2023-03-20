package com.example.milashuk.separateResponsabilities.resourceServer.service;

import com.example.milashuk.separateResponsabilities.resourceServer.domain.security.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthenticationServerProxy {

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String userName, String password) {
        String url = baseUrl + "/user/auth";

        UserDto user = new UserDto()
                .setUserName(userName)
                .setPassword(password);

        restTemplate.postForEntity(url, new HttpEntity<>(user), Void.class);
    }

    public boolean sendOtp(String userName, String code) {
        String url = baseUrl + "/otp/check";

        UserDto user = new UserDto()
                .setUserName(userName)
                .setCode(code);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, new HttpEntity<>(user), Void.class);

        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
