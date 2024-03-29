package com.example.milashuk.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.function.Function;

@Configuration
@EnableReactiveMethodSecurity
public class ProjectConfig {

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        var user = User
                .withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET, "hello").authenticated()
                .anyExchange().access(this::getAuthorizationDecisionMono)
                .and().formLogin()
                .and().build();
    }

    private Mono<AuthorizationDecision> getAuthorizationDecisionMono(
            Mono<Authentication> authenticationMono,
            AuthorizationContext authorizationContext) {
        String requestPath = getRequestPath(authorizationContext);

        boolean restrictedTime = LocalTime.now().isAfter(LocalTime.NOON);

        if (requestPath.equals("/ciao")) {
            return authenticationMono
                    .map(isAdmin())
                    .map(auth -> auth && restrictedTime)
                    .map(AuthorizationDecision::new);
        }

        return Mono.just(new AuthorizationDecision(false));
    }

    private String getRequestPath(AuthorizationContext authorizationContext) {
        return authorizationContext
                .getExchange()
                .getRequest()
                .getPath()
                .toString();
    }

    private Function<Authentication, Boolean> isAdmin() {
        return p -> p
                .getAuthorities()
                .stream()
                .anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"));
    }
}
