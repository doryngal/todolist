package com.todo.todolist.repository;

import com.todo.todolist.model.Todo;
import com.todo.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long userId);
    List<Todo> findByUser(User user);
}

