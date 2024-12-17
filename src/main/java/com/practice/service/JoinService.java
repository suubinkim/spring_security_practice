package com.practice.service;

import com.practice.dto.JoinDto;
import com.practice.entity.User;
import com.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * com.practice.service
 *   |_ joinService
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/11/24 10:55 AM
 * @description : joinService
 */
@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) {
        String username = joinDto.getUsername();
        String password = joinDto.getPassword();
        String email = joinDto.getEmail();
        String name = joinDto.getName();
        Boolean exists = userRepository.existsByUsername(username);

        if (Boolean.TRUE.equals(exists)) return;

        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }

}
