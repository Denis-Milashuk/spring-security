package com.example.milashuk.prePostAuth.service;

import com.example.milashuk.prePostAuth.domain.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.lang.String;
import java.io.Serializable;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        String p = (String) permission;

        boolean isAdmin = authentication
                .getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals(p));

        return isAdmin || document.owner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
