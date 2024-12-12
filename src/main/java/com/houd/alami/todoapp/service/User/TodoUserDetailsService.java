package com.houd.alami.todoapp.service.User;

import com.houd.alami.todoapp.model.TodoUser;
import com.houd.alami.todoapp.repository.TodoUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TodoUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final TodoUserRepository todoUserRepository;

    public TodoUserDetailsService(TodoUserRepository todoUserRepository) {
        this.todoUserRepository = todoUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        TodoUser todoUser = todoUserRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(todoUser.getPseudo())
                .password(todoUser.getPassword())
                .build();
    }
}

//Spring Security attend un UserDetailsService pour récupérer les informations de l'utilisateur pendant l'authentification.
//La méthode loadUserByUsername permet de récupérer un utilisateur par son pseudo.
//Nous retournons un objet User de Spring Security, qui contient le pseudo, le mot de passe.