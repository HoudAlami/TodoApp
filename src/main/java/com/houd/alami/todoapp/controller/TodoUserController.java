package com.houd.alami.todoapp.controller;

import com.houd.alami.todoapp.model.TodoUser;
import com.houd.alami.todoapp.service.User.TodoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")  // Permet les requêtes CORS depuis localhost:3000
public class TodoUserController {
    private  final TodoUserService todoUserService;

    @Autowired
    public TodoUserController(TodoUserService todoUserService) {
        this.todoUserService = todoUserService;
    }

    @PostMapping
    public TodoUser createUser(@RequestBody TodoUser todoUser){
        return todoUserService.createUser(todoUser);
    }

    @GetMapping
    public List<TodoUser> getUser() {
        return todoUserService.getAllUsers();
    }
}
