package com.example.milashuk.separateResponsabilities.authServer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Setter
@Getter
@Accessors(chain = true)
public class Otp {
    @Id
    private String userName;
    private String code;
}
