package com.practice.controller;

import com.practice.dto.ResponseDto;
import com.practice.service.CustomUserDetails;
import com.practice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * com.practice.controller
 *   |_ UserController
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/18/24 8:26 AM
 * @description : UserController
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseDto getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername();
        log.info("username = {}", username);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(username);
        return responseDto;
    }

    @PostMapping("/oauth2-jwt-header")
    public String oauth2JwtHeader(HttpServletRequest request, HttpServletResponse response) {
        return userService.oauth2JwtHeaderSet(request, response);
    }
}
