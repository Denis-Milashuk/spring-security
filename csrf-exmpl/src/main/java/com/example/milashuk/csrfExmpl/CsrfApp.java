package com.example.milashuk.csrfExmpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CsrfApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CsrfApp.class);
        Object springSecurityFilterChain = context.getBean("springSecurityFilterChain");
    }
}