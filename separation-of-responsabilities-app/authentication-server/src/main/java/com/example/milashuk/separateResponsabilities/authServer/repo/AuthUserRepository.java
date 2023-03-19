package com.example.milashuk.separateResponsabilities.authServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.milashuk.separateResponsabilities.authServer.domain.AuthUser;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findAuthUserByUserName(String userName);
}
