package com.example.authorizationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthorizationAppApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(AuthorizationAppApplication.class, args);
        Object springSecurityFilterChain = context.getBean("springSecurityFilterChain");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/ciao")
    public String ciao() {
        return "Ciao";
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hola";
    }
}
