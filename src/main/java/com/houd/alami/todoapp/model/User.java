package com.houd.alami.todoapp.model;

import jakarta.persistence.*;

@Entity // J'indique qu'il s'agit d'une entité de ma BDD
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String pseudo;
    private String password;
    private boolean isAuth = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "todolist_id")
    private TodoList todoList;

    public User() {
        // Constructeur utilisé par JPA
    }

    // J'indique dans le constructeur les valeurs qui ne changeront jamais, qui seront instancié directement à la création de l'objet
    public User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
        // Je n'indique pas isAuth car cette valeur sera modifié dynamiquement
    }

    public String getPseudo() {
        return pseudo;
    }
    // Pas de setter car cette donnée ne changera pas dans le futur

    public String getPassword() {
        return password;
    }
    // Pas de setter car cette donnée ne changera pas dans le futur

    public Boolean isAuth() {
        return isAuth;
    }
    // Pas de setter car cette donnée sera modifié directement dans le service pour respecter la logique métier



}
