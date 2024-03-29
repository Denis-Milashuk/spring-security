package com.example.milashuk.filteringWithDataJpa.controller;

import com.example.milashuk.filteringWithDataJpa.domain.Product;
import com.example.milashuk.filteringWithDataJpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/{text}")
    public List<Product> findProductsContaining(@PathVariable String text) {
        return productRepository.findProductsByNameContains(text);
    }
}
