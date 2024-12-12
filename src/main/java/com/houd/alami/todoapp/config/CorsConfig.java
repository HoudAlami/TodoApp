package com.houd.alami.todoapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autoriser CORS sur toutes les routes
                .allowedOrigins("http://localhost:8080")  // Remplacez par l'URL de votre front-end (si c'est React, Vue.js, etc.)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Permet ces méthodes HTTP
                .allowedHeaders("*")  // Autoriser tous les en-têtes
                .allowCredentials(true);  // Permet les cookies et les informations d'authentification
    }
}
