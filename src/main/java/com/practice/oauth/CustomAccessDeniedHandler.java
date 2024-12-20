package com.practice.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <pre>
 * com.practice.oauth
 *   |_ CustomAccessDeniedHandler
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/20/24 1:24 PM
 * @description : CustomAccessDeniedHandler
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 리다이렉트 대신 HTTP 상태 코드와 메시지 반환
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Forbidden\"}");
    }
}
