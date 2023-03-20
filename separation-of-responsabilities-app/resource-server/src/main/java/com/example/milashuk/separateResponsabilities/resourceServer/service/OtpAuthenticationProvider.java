package com.example.milashuk.separateResponsabilities.resourceServer.service;

import com.example.milashuk.separateResponsabilities.resourceServer.domain.security.OtpAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {
    
    private final AuthenticationServerProxy proxy;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());

        boolean isCodeValid = proxy.sendOtp(name, code);

        if(isCodeValid) {
            return new OtpAuthentication(name, code);
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.isAssignableFrom(authentication);
    }
}
