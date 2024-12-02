package com.houd.alami.todoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity // Annonce que cette classe est une entité persistante dans la base de données (BDD).
public class TodoList {
    @Id // Cette annotation indique que l'attribut "id" est la clé primaire de l'entité.
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-génération de l'ID
    private int id; // Déclaration de l'attribut id (clé primaire).

    @NotNull(message = "Le nom est obligatoire") // Cette annotation indique que le champ "name" ne peut pas être nul.
    private String name; // Attribut pour stocker le nom de la TodoList.

    @OneToOne(mappedBy = "todoList") // Relation de type "One to One" avec la classe "User". "mappedBy" signifie que l'entité "User"
    // gère la relation (la clé étrangère est dans la table "User").
    private User user; // L'utilisateur associé à cette TodoList.

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL) // Relation "One to Many" avec la classe "Task". "mappedBy" indique que l'attribut
    // "todoList" de "Task" est responsable de la relation.
    // Une TodoList peut être associée à plusieurs tâches, donc un à plusieurs.
    private List<Task> tasks = new ArrayList<>(); // Liste des tâches associées à cette TodoList, initialisée comme une nouvelle ArrayList.

    public TodoList() {
        // Constructeur sans arguments utilisé par JPA lors de l'instanciation de l'objet depuis la base de données.
    }

    // Constructeur personnalisé pour créer une TodoList avec un nom et un utilisateur.
    public TodoList(String name, User user) {
        this.name = name; // Initialisation de l'attribut "name" avec le nom passé en paramètre.
        this.user = user; // Initialisation de l'attribut "user" avec l'utilisateur passé en paramètre.
        //Pas d'initialisation de tâche
    }

    // Getter pour le nom de la TodoList
    public String getName() {
        return name; // Retourne le nom de la TodoList.
    }

    // Setter pour le nom de la TodoList
    public void setName(String name) {
        this.name = name; // Assigne la valeur du nom à l'attribut "name".
    }

    // Getter pour l'utilisateur associé à la TodoList
    public User getUser() {
        return user; // Retourne l'utilisateur associé à cette TodoList.
    }

    // Setter pour l'utilisateur associé à la TodoList
    public void setUser(User user) {
        this.user = user; // Assigne l'utilisateur à l'attribut "user".
    }
}
