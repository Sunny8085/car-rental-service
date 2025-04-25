package com.car.rentalservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${websocket.allowed-origins}")
    private String allowedOrigins;

    @Value("${websocket.endpoint}")
    private String websocketEndpoint;
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping(websocketEndpoint)
		        .allowedOriginPatterns(allowedOrigins)
		        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		        .allowedHeaders("*")
		        .allowCredentials(true)
		        .maxAge(3600); 
    }
}		
