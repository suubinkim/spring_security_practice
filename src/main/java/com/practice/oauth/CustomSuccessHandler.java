package com.practice.oauth;

import com.practice.entity.Refresh;
import com.practice.repository.RefreshRepository;
import com.practice.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * <pre>
 * com.practice.oauth
 *   |_ CustomSuccessHandler
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/13/24 2:38 PM
 * @description : CustomSuccessHandler
 */
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();
        String name = customUserDetails.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt("access", username, role, 60 * 60 * 60L);
        String refresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장
        addRefreshEntity(username, refresh, 86400000L);


        //응답 설정
        response.addCookie(createCookie("access", token));
        response.addCookie(createCookie("refresh", refresh));

        // redirect query param 인코딩 후 전달
        // 이후에 JWT 를 읽어서 데이터를 가져올 수도 있지만, JWT 파싱 비용이 많이 들기 때문에
        // 처음 JWT 발급할 때 이름을 함께 넘긴 후, 로컬 스토리지에 저장한다.
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        response.sendRedirect("http://localhost:3001/oauth2-jwt-header?name=" + encodedName);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = new Refresh();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
}
