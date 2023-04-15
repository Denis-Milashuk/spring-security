package com.example.milashuk.prePostAuth.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private final Map<String, List<String>> secretNames = Map.of("Dima", List.of("Dimitrus"), "Artem", List.of("Artemka"));

    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "Fantastico";
    }

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }
}
