package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//cros이슈 해결방법으로 추가함
//front-end와 back-end포트번호다를때사용함

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 요청에 대해서
                .allowedOrigins("http://localhost:8000/", "http://localhost:3000/"); // 허용할 오리진들
        // .allowedOrigins("*");
    }
}