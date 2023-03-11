package com.example.milashuk.filterChain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FilterChainMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FilterChainMain.class);
        Object springSecurityFilterChain = context.getBean("springSecurityFilterChain");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }
}
