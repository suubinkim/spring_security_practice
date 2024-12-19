package com.practice.service;

import com.practice.entity.User;
import com.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * com.practice.service.impl
 *   |_ CustomUserDetailsService
 * </pre>
 * 
 * @Author  : subin1
 * @Date    : 12/11/24 3:44 PM
 * @description : CustomUserDetailsService
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new CustomUserDetails(user);
        }
        return null;
    }
}
