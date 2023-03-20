package com.example.milashuk.separateResponsabilities.authServer.service;

import com.example.milashuk.separateResponsabilities.authServer.domain.AuthUser;
import com.example.milashuk.separateResponsabilities.authServer.domain.Otp;
import com.example.milashuk.separateResponsabilities.authServer.repo.AuthUserRepository;
import com.example.milashuk.separateResponsabilities.authServer.repo.OtpRepository;
import com.example.milashuk.separateResponsabilities.authServer.util.GenerateCodeUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;
    private final OtpRepository otpRepository;

    public void addUser(AuthUser authUser) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUserRepository.save(authUser);
    }

    public void auth (AuthUser authUser) {
        Optional<AuthUser> user = authUserRepository.findAuthUserByUserName(authUser.getUserName());

        if(user.isPresent() && passwordEncoder.matches(authUser.getPassword(), user.get().getPassword())) {
            renewOtp(authUser);
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    public boolean check(Otp otpToValidate){
        Optional<Otp> userOtp = otpRepository.findOtpByUserName(otpToValidate.getUserName());

        return userOtp
            .map(Otp::getCode)
            .filter(code -> code.equals(otpToValidate.getCode()))
            .isPresent();
    }

    private void renewOtp(AuthUser authUser) {
        String code = GenerateCodeUtil.generateCode();
        otpRepository.save(new Otp()
                .setUserName(authUser.getUserName())
                .setCode(code));
    }
}
