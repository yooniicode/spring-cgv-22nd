package com.ceos22.spring_boot.common.auth.security.jwt;

import com.ceos22.spring_boot.common.exception.AuthFailureException;
import com.ceos22.spring_boot.common.response.ApiResponse;
import com.ceos22.spring_boot.common.response.status.ErrorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception) throws IOException {
        ErrorStatus status;
        String message = exception.getMessage();

        if (exception instanceof AuthFailureException afe) {
            status = afe.getErrorStatus();
        } else {
            status = ErrorStatus._UNAUTHORIZED;
        }

        var responseEntity = ApiResponse.onFailure(status);

        response.setStatus(status.getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(response.getWriter(), responseEntity.getBody());
    }
}
