package com.example.milashuk.prePostAuth.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class NameServiceTest {

    @Autowired
    private NameService nameService;

    @Test
    void getNameWithNoUser() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> nameService.getName());
    }

    @Test
    @WithMockUser(authorities = "write")
    void getNameWithMockUser() {
        assertEquals("Fantastico", nameService.getName());
    }

    @Test
    @WithMockUser(authorities = "read")
    void testNameServiceWithUserButWrongAuthority() {
        assertThrows(AccessDeniedException.class,
                () -> nameService.getName());
    }
}