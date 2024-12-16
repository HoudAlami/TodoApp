package com.houd.alami.todoapp.service.User;
import com.houd.alami.todoapp.model.TodoUser;
import com.houd.alami.todoapp.repository.TodoUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoUserService {

    private final TodoUserRepository todoUserRepository;


    @Autowired
    public TodoUserService(TodoUserRepository todoUserRepository) {

        this.todoUserRepository = todoUserRepository;
    }

    @PostConstruct
    public void createDefaultUser() {
        if (!todoUserRepository.existsByPseudo("TestUser")) {
            TodoUser testUser = new TodoUser();
            testUser.setPseudo("TestUser");
            testUser.setPassword("test1234");
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

    public List<TodoUser> getAllUsers() {
        return todoUserRepository.findAll();
    }
}



