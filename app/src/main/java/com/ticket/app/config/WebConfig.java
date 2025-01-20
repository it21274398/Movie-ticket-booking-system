package com.ticket.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configuration class to set up CORS mapping for the application
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // This method configures Cross-Origin Resource Sharing (CORS) settings
    @SuppressWarnings("null") // Suppresses warnings related to null values (may not be necessary here)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configures the allowed origins, methods, and headers for CORS requests
        registry.addMapping("/**")  // Apply CORS settings to all paths (/**)
                .allowedOrigins("http://localhost:3000") // Allow requests from the specified frontend URL (React app running on localhost:3000)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow the specified HTTP methods
                .allowedHeaders("*"); // Allow all headers in the request
    }
}
