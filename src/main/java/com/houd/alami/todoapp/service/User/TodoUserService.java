package com.houd.alami.todoapp.service.User;
import com.houd.alami.todoapp.model.TodoUser;
import com.houd.alami.todoapp.repository.TodoUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoUserService {

    private final TodoUserRepository todoUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TodoUserService(TodoUserRepository todoUserRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.todoUserRepository = todoUserRepository;
    }

    @PostConstruct
    public void createDefaultUser() {
        if (!todoUserRepository.existsByPseudo("TestUser")) {
            TodoUser testUser = new TodoUser();
            testUser.setPseudo("TestUser");
            testUser.setPassword(passwordEncoder.encode("test1234"));

            createUser(testUser);
        }
    }

    // Crée un nouvel utilisateur dans la base de données
    public TodoUser createUser(TodoUser todoUser) {
        if (todoUserRepository.existsByPseudo(todoUser.getPseudo())) {
            throw new IllegalArgumentException("Pseudo already exists" + todoUser.getPseudo());
        }
        return todoUserRepository.save(todoUser);
    }

    public List<TodoUser> getAllUsers(){
        return todoUserRepository.findAll();
    }

    // Vérifie les identifiants d'un utilisateur (pseudo et mot de passe)
    public boolean loginUser(String pseudo, String password) {
        TodoUser todoUser = todoUserRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!todoUser.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }

        return true;
    }

    public void logoutUser() {

    }
}
