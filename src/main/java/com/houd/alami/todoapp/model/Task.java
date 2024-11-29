package com.houd.alami.todoapp.model;

import com.houd.alami.todoapp.Enums.Status;
import jakarta.persistence.*;

@Entity //J'indique qu'il s'agit d'un entité de ma BDD
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private Status status;
    private String category;

    @ManyToOne // Plusieurs taches peuvent etre associé a une seul TodoList
    @JoinColumn(name = "todolist_id")
    private TodoList todoList;
}
