package com.example.milashuk.basic.controller;

import com.example.milashuk.basic.config.WithCustomUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Hello endpoint should return Unauthorized(403)")
    public void helloUnauthorized() throws Exception {
        mockMvc
                .perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "Dima")
    @DisplayName("Hello endpoint should return ok(200)")
    public void helloAuthenticated() throws Exception {
        mockMvc
                .perform(get("/hello"))
                .andExpect(content().string("Hello, Dima"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Implementation without @WithMockUser annotation")
    public void helloWithRequestPostProcessor() throws Exception {
        mockMvc
                .perform(get("/hello").with(user("Dima")))
                .andExpect(content().string("Hello, Dima"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("Dima")
    @DisplayName("Implementations with user from UserDetails")
    public void helloWithUserDetails() throws Exception {
        mockMvc
                .perform(get("/hello"))
                .andExpect(content().string("Hello, Dima"))
                .andExpect(status().isOk());
    }

    @Test
    @WithCustomUser(userName = "Artem")
    @DisplayName("With custom Security context")
    public void helloWithCustomSecurityContext() throws Exception {
        mockMvc
                .perform(get("/hello"))
                .andExpect(content().string("Hello, Artem"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing auth logic")
    public void helloAuthTest() throws Exception {
        mockMvc
                .perform(get("/hello").with(httpBasic("Dima", "password")))
                .andExpect(status().isOk());
    }
}