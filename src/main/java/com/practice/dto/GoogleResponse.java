package com.practice.dto;

import java.util.Map;

/**
 * <pre>
 * com.practice.dto
 *   |_ GoogleResponse
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/13/24 2:04 PM
 * @description : GoogleResponse
 */
public class GoogleResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) {

        this.attribute = attribute;
    }

    @Override
    public String getProvider() {

        return "google";
    }

    @Override
    public String getProviderId() {

        return attribute.get("sub").toString();
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
