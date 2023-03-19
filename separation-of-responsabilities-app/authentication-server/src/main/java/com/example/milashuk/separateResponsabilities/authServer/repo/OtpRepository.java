package com.example.milashuk.separateResponsabilities.authServer.repo;

import com.example.milashuk.separateResponsabilities.authServer.domain.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findOtpByUserName(String userName);
}
