package com.example.milashuk.oauth2Server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class DefaultSecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> {auth.anyRequest().authenticated();})
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
