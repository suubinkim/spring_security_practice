package com.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * com.practice.config
 *   |_ WebConfig
 * </pre>
 * 
 * @Author  : subin1
 * @Date    : 12/12/24 10:19 AM
 * @description : WebConfig
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**")
            .allowedOrigins("http://localhost:3000");
    }
}
