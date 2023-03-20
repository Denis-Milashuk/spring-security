package com.example.loginformapp.domain.entity;

import com.example.loginformapp.domain.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Setter
@Getter
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
