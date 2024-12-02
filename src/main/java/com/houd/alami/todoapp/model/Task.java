package com.houd.alami.todoapp.model;

import com.houd.alami.todoapp.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity //J'indique qu'il s'agit d'un entité de ma BDD
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-génération de l'ID pour la tâche
    private int id;

    @NotNull(message = "Le titre de la tâche est obligatoire") // Validation que le titre de la tâche ne peut pas être null
    private String title;

    @NotNull(message = "La description de la tâche est obligatoire")
    private String description;

    @NotNull(message = "Le statut de la tâche est obligatoire")
    private Status status;

    @ManyToOne // Annotation pour définir la relation Many-to-One avec TodoList. Une ou plusieurs tâches appartient à une TodoList.
    @JoinColumn(name = "todolist_id") // Spécifie la colonne "todolist_id" dans la base de données pour la clé étrangère
    private TodoList todoList; // Lien vers la TodoList à laquelle cette tâche appartient

    public Task() {
        // Constructeur utilisé par JPA
    }

    // Constructeur personnalisé pour créer une tâche avec un titre, une description, un statut et une TodoList
    public Task(String title, String description, Status status, TodoList todoList) {
        this.title = title;
        this.description = description;
        this.status = Status.TODO; // Par défaut, la tâche est initialisée avec un statut à faire
        this.todoList = todoList; // Associe la tâche à une TodoList spécifique
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }


}
