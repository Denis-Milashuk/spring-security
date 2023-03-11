package com.example.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService () {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User
                .withUsername("Dima")
                .password("123654")
                .roles("MANAGER")
                .build());
        userDetailsManager.createUser(User
                .withUsername("Denis")
                .password("123654")
                .roles("ADMIN")
                .build());

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello").hasRole("ADMIN")
                        .requestMatchers("/ciao").hasRole("MANAGER")
                        .requestMatchers("/a/b/**").authenticated()
                        .requestMatchers("/product/{code:^[0-9]*$}").permitAll()
                        .requestMatchers(RegexRequestMatcher.regexMatcher(".*/(us|uk|ca)+/(en|fr).*")).authenticated()
                        .anyRequest().denyAll())
                .httpBasic()
                .and()
                .build();
    }
}


