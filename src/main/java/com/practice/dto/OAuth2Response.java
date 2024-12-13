package com.practice.dto;

/**
 * <pre>
 * com.practice.dto
 *   |_ OAuth2Response
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/13/24 2:03 PM
 * @description : OAuth2Response
 */
public interface OAuth2Response {

    //제공자 (Ex. naver, google, ...)
    String getProvider();

    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();

    //이메일
    String getEmail();

    //사용자 실명 (설정한 이름)
    String getName();
}
