package com.example.milashuk.basic.ustil;

import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Data
@Component
public class AsyncUtil {

    @Async
    public void asyncMethod() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication.getName());
    }
}
