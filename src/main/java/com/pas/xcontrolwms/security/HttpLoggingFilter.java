package com.pas.xcontrolwms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
public class HttpLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        long start = System.currentTimeMillis();

        logRequest(request);

        filterChain.doFilter(request, response);

        logResponse(response, start);
    }

    private void logRequest(HttpServletRequest request) {

        log.info("========== Incoming Request ==========");

        log.info("Method       : {}", request.getMethod());
        log.info("URI          : {}", request.getRequestURI());
        log.info("Remote Addr  : {}", request.getRemoteAddr());
        log.info("Content-Type : {}", request.getContentType());
        log.info("Content-Length : {}", request.getContentLengthLong());

        Collections.list(request.getHeaderNames())
                .forEach(header -> {

                    String value = request.getHeader(header);

                    if ("authorization".equalsIgnoreCase(header)) {
                        value = "***MASKED***";
                    }

                    log.info("{} = {}", header, value);
                });

        log.info("======================================");
    }

    private void logResponse(
            HttpServletResponse response,
            long start) {

        log.info("========== Response ==========");

        log.info("Status      : {}", response.getStatus());
        log.info("Duration    : {} ms",
                System.currentTimeMillis() - start);

        log.info("==============================");
    }
}