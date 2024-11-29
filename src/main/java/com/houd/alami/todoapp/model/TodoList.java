package com.houd.alami.todoapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-génération de l'ID

    private int id;
    private String name;

    @OneToOne(mappedBy = "todoList") // User est propriétaire, TodoList est l'inverse (mappedBy)
    private User user;

    @OneToMany(mappedBy = "todolist", cascade = CascadeType.ALL) // Relation avec Task, propagée par cascade
    //Une todolist peut etre associé a plusieurs taches
    private List<Task> tasks = new ArrayList<>();
}
