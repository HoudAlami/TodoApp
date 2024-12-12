package com.houd.alami.todoapp.config;
import com.houd.alami.todoapp.service.User.TodoUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TodoUserDetailsService todoUserDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, TodoUserDetailsService todoUserDetailsService) {
        this.jwtService = jwtService;
        this.todoUserDetailsService = todoUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Récupère le header Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Retire "Bearer " pour récupérer le token pur

            // Extrait le nom d'utilisateur à partir du token
            String username = jwtService.extractUsername(token);

            // Vérifie si l'utilisateur n'est pas déjà authentifié
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Charge les détails utilisateur
                UserDetails userDetails = todoUserDetailsService.loadUserByUsername(username);

                // Vérifie la validité du token
                if (jwtService.isTokenValid(token, userDetails)) {
                    // Crée un objet d'authentification
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken); // Ajoute l'authentification
                }
            }
        }

        // Continue le traitement de la requête
        filterChain.doFilter(request, response);
    }
}
