package com.houd.alami.todoapp.controller;

import com.houd.alami.todoapp.config.JwtService;
import com.houd.alami.todoapp.service.User.TodoUserDetailsService;
import com.houd.alami.todoapp.model.TodoUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtService jwtService;
    private final TodoUserDetailsService todoUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtService jwtService, TodoUserDetailsService todoUserDetailsService, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.todoUserDetailsService = todoUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String pseudo, @RequestParam String password) {
        // Charger l'utilisateur depuis le UserDetailsService
        UserDetails userDetails = todoUserDetailsService.loadUserByUsername(pseudo);

        // Vérifier que l'utilisateur existe et que le mot de passe est correct
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // Si l'utilisateur est authentifié, générer un token JWT
        String token = jwtService.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }


    // Classe interne pour la réponse contenant le token JWT
    public static class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
