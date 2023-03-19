package com.example.milashuk.csrfExmpl.config;

import jakarta.servlet.*;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;
import java.util.logging.Logger;

public class CsrfTokenLogger implements Filter {

    private final Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CsrfToken csrf = (CsrfToken)request.getAttribute("_csrf");

        logger.info("CSRF token " + csrf.getToken());

        chain.doFilter(request, response);
    }
}
