package com.practice.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <pre>
 * com.practice.oauth
 *   |_ CustomAuthenticationEntryPoint
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/20/24 1:22 PM
 * @description : CustomAuthenticationEntryPoint
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // 리다이렉트 대신 HTTP 상태 코드와 메시지 반환
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized access\"}");
    }
}
