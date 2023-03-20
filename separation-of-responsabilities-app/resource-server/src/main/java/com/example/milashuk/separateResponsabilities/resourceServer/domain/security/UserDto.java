package com.example.milashuk.separateResponsabilities.resourceServer.domain.security;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private String userName;
    private String password;
    private String code;
}
