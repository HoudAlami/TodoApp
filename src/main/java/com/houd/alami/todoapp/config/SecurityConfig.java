package com.houd.alami.todoapp.config;

import com.houd.alami.todoapp.service.User.TodoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtService jwtService;
    private final TodoUserDetailsService todoUserDetailsService;

    public SecurityConfig(JwtService jwtService, TodoUserDetailsService todoUserDetailsService) {
        this.jwtService = jwtService;
        this.todoUserDetailsService = todoUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Désactive la protection CSRF (non nécessaire ici avec JWT)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/users").permitAll()// Routes publiques
                        .anyRequest().authenticated() // Routes protégées
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, todoUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class); // Ajout du filtre JWT

        return http.build(); // Construit et retourne la configuration de sécurité
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
