package com.houd.alami.todoapp.repository;

import com.houd.alami.todoapp.model.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUser, Integer> {

    Optional<TodoUser> findByPseudo(String pseudo);
    boolean existsByPseudo(String pseudo);

}
