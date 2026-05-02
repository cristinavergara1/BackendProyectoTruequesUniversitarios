package com.trueques.backend.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
        // OJO: si allowCredentials(true), NO se puede usar '*' en allowedOrigins.
        // Usamos allowedOriginPatterns para cubrir previews de Vercel sin abrir todo a cualquier origen.
        .allowedOriginPatterns(
            "http://localhost:5173",
            "http://localhost:3000",
            "https://udea-trueques.vercel.app",
            "https://*.vercel.app"
        )
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .exposedHeaders("Authorization")
        .allowCredentials(true)
                .maxAge(3600);
    }
}
