package com.tlu.unigrade.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class SecurityEntryPointConfig {

    @Bean
    public AuthenticationEntryPoint noPopupBasicAuthEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                {
                  "error": "Unauthorized",
                  "message": "Invalid username or password"
                }
            """);
        };
    }
}
