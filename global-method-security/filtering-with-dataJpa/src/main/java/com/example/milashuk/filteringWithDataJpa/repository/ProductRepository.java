package com.example.milashuk.filteringWithDataJpa.repository;

import com.example.milashuk.filteringWithDataJpa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:text% AND p.owner =?#{authentication.name}")
    List<Product> findProductsByNameContains(@Param("text") String text);
}
