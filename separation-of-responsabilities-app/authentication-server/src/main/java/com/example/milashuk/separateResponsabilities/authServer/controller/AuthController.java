package com.example.milashuk.separateResponsabilities.authServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.milashuk.separateResponsabilities.authServer.domain.AuthUser;
import com.example.milashuk.separateResponsabilities.authServer.domain.Otp;
import com.example.milashuk.separateResponsabilities.authServer.service.UserService;

@RestController
public class AuthController{

    @Autowired
    private UserService userService;

    @PostMapping("/user/add")
    public void addUser(@RequestBody AuthUser authUser) {
        userService.addUser(authUser);
    }

    @PostMapping("user/auth")
    public void auth(@RequestBody AuthUser authUser) {
        userService.auth(authUser);
    }

    @PostMapping("otp/check")
    public ResponseEntity<String> check(@RequestBody Otp otp) {
        return userService.check(otp) 
            ? ResponseEntity.ok().build() 
            : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}