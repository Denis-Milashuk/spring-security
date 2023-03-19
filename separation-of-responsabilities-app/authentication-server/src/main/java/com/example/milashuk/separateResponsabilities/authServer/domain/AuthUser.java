package com.example.milashuk.separateResponsabilities.authServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class AuthUser {
    @Id
    private String userName;
    private String password;
}
