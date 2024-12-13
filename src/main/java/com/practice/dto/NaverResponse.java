package com.practice.dto;

import java.util.Map;

/**
 * <pre>
 * com.practice.dto
 *   |_ NaverResponse
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/13/24 2:03 PM
 * @description : NaverResponse
 */
public class NaverResponse implements OAuth2Response {
    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {

        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {

        return "naver";
    }

    @Override
    public String getProviderId() {

        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {

        return attribute.get("email").toString();
    }

    @Override
    public String getName() {

        return attribute.get("name").toString();
    }
}
