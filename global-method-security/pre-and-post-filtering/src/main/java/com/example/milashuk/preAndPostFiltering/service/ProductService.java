package com.example.milashuk.preAndPostFiltering.service;

import com.example.milashuk.preAndPostFiltering.domain.Product;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @PreFilter("filterObject.owner() == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        return products;
    }

    @PostFilter("filterObject.owner() == authentication.name")
    public List<Product> findProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("beer", "Denis"));
        products.add(new Product("candy", "Dima"));
        products.add(new Product("chocolate", "Dima"));

        return products;
    }
}
