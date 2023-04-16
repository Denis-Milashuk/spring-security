package com.example.milashuk.preAndPostFiltering.controller;

import com.example.milashuk.preAndPostFiltering.domain.Product;
import com.example.milashuk.preAndPostFiltering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/sell")
    public List<Product> sellProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("beer", "Denis"));
        products.add(new Product("candy", "Dima"));
        products.add(new Product("chocolate", "Dima"));

        return productService.sellProducts(products);
    }

    @GetMapping("/find")
    public List<Product> findProducts() {
        return productService.findProducts();
    }
}
