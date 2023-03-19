package com.example.milashuk.csrfExmpl.repo;

import com.example.milashuk.csrfExmpl.domain.entity.CustomCsrfToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final JpafTokenRepository jpafTokenRepository;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String tocken = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", tocken);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<CustomCsrfToken> existingTokenOpt = jpafTokenRepository.findCustomCsrfTokenByIdentifier(identifier);

        if (existingTokenOpt.isPresent()) {
            CustomCsrfToken existiongToken = existingTokenOpt.get();
            existiongToken.setToken(token.getToken());
            jpafTokenRepository.save(existiongToken);
        } else {
            CustomCsrfToken newToken = new CustomCsrfToken();
            newToken.setToken(token.getToken());
            newToken.setIdentifier(identifier);
            jpafTokenRepository.save(newToken);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<CustomCsrfToken> existingTokenOpt = jpafTokenRepository.findCustomCsrfTokenByIdentifier(identifier);

        if (existingTokenOpt.isPresent()) {
            CustomCsrfToken existingToken = existingTokenOpt.get();

            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", existingToken.getToken());
        }

        return null;
    }
}
