package com.example.milashuk.filterChain.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!((request instanceof HttpServletRequest httpRequest) && (response instanceof HttpServletResponse httpResponse))) {
            throw new ServletException("RequestValidationFilter only supports HTTP requests");
        }

        String requestHeader = httpRequest.getHeader("request-id");

        if (requestHeader == null || requestHeader.isBlank()) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        chain.doFilter(request, response);
    }
}
