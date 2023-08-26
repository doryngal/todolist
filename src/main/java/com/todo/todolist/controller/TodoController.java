package com.todo.todolist.controller;

import com.todo.todolist.model.ApiResponse;
import com.todo.todolist.model.Todo;
import com.todo.todolist.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/getAllTodos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> result = todoService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getTodoById/{id}")
    public ResponseEntity<String> getTodoTitleById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todo.getTitle());
    }

    @GetMapping("/myTodos/{userId}")
    public ResponseEntity<List<Todo>> getMyTodos(@PathVariable Long userId) {
        List<Todo> myTodos = todoService.getTodosByUserId(userId);
        return ResponseEntity.ok(myTodos);
    }

    @PostMapping("/createTodoForUser/{userId}")
    public ResponseEntity<Todo> createTodoForUser(@PathVariable Long userId, @RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodoForUser(userId, todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody Todo todoDetails) {
        ResponseEntity<ApiResponse> response = todoService.update(id, todoDetails);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully");
    }
}
