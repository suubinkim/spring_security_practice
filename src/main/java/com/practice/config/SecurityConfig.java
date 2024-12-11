package com.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <pre>
 * com.practice.config
 *   |_ SecurityConfig
 * </pre>
 * 
 * @Author  : subin1
 * @Date    : 12/11/24 9:04 AM
 * @description : SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http
            .csrf(AbstractHttpConfigurer::disable);

        //From 로그인 방식 disable
        http
            .formLogin(AbstractHttpConfigurer::disable);

        //http basic 인증 방식 disable
        http
            .httpBasic(AbstractHttpConfigurer::disable);

        //경로별 인가 작업
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/", "/join").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated());

        //세션 설정
        http
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
