package com.todo.todolist.service;

import com.todo.todolist.model.ApiResponse;
import com.todo.todolist.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/api")
public interface TodoService {
    List<Todo> getAll();
    Todo getTodoById(Long id);
    ResponseEntity<ApiResponse> update(Long id, Todo todoDetails);
    ResponseEntity<ApiResponse> deleteTodo(Long id);
    List<Todo> getTodosByUserId(Long userId);
    Todo createTodoForUser(Long userId, Todo todo);
    List<Todo> getTodosByUser(Long userId);
}
