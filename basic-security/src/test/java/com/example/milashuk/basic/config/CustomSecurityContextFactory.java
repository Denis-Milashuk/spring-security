package com.example.milashuk.basic.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

public class CustomSecurityContextFactory implements WithSecurityContextFactory<WithCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(annotation.userName(), null, List.of(new SimpleGrantedAuthority("read")));

        context.setAuthentication(auth);
        return context;
    }
}
