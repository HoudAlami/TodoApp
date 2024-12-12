package com.houd.alami.todoapp.repository;

import com.houd.alami.todoapp.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository <TodoList, Integer > {
}
