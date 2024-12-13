package com.practice.oauth;

import com.practice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * <pre>
 * com.practice.oauth
 *   |_ CustomOAuth2User
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/13/24 2:10 PM
 * @description : CustomOAuth2User
 */
@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final User user;

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.emptyMap();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add((GrantedAuthority) user::getRole);

        return collection;
    }

    @Override
    public String getName() {
        return user.getName();
    }

    public String getUsername() {
        return user.getUsername();
    }
}
