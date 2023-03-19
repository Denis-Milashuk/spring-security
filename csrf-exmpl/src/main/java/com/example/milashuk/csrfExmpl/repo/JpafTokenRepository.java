package com.example.milashuk.csrfExmpl.repo;

import com.example.milashuk.csrfExmpl.domain.entity.CustomCsrfToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpafTokenRepository extends JpaRepository<CustomCsrfToken, Long> {
    Optional<CustomCsrfToken> findCustomCsrfTokenByIdentifier(String identifier);
}
