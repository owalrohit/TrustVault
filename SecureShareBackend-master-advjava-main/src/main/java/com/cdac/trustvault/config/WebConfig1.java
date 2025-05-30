package com.cdac.trustvault.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig1 implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all paths
                .allowedOrigins("http://localhost:5173")  // React app URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials (cookies, authorization headers)
    }
}
