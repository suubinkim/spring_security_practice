package com.practice.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * com.practice.service
 *   |_ UserService
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/18/24 4:04 PM
 * @description : UserService
 */
@Service
public class UserService {

    public String oauth2JwtHeaderSet(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String access = null;

        if(cookies == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "bad";
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("access")){
                access = cookie.getValue();
            }
        }

        if(access == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "bad";
        }

        // 클라이언트의 access 토큰 쿠키를 만료
        response.addCookie(createCookie("access", null));
        response.addHeader("access", access);
        response.setStatus(HttpServletResponse.SC_OK);

        return "success";
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(false);

        return cookie;
    }
}
