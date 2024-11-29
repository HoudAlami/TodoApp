package com.houd.alami.todoapp.Enums;

public enum Status {
    TODO("A faire"), // Statut à faire avec sa description
    IN_PROGRESS("En cours"), // Statut en cours avec sa description
    DONE("Terminé"); // Statut Terminé avec sa description

    private final String description; // Attribut description associé à chaque statut

    // Constructeur de l'enum permettant de définir la description pour chaque constante
    Status(String description) {
        this.description = description;
    }

    // Méthode getter pour recuperer la description du statut
    public String getDescription() {
        return description;
    }
}
