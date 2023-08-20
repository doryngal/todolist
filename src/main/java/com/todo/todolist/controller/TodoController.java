package com.todo.todolist.controller;

import com.todo.todolist.repository.TodoRepository;
import com.todo.todolist.model.ApiResponse;
import com.todo.todolist.model.ResourceNotFoundException;
import com.todo.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTodos() {
        try {
            List<Todo> todos = todoRepository.findAll();
            return ResponseEntity.ok(new ApiResponse(true, "Todo retrieved successfully", todos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error retrieving todos", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTodoById(@PathVariable Long id) {
        try {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new ResolutionException("Todo not found with id: " + id));

            return ResponseEntity.ok(new ApiResponse(true, "Todo retrieved successfully", todo));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error retrieving todo with id: " + id, null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTodo(@RequestBody Todo todo) {
        try {
            Todo createdTodo = todoRepository.save(todo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Todo created success", createdTodo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error creating todo", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody Todo todoDetails) {
        try {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
            todo.setTitle(todoDetails.getTitle());
            todo.setCompleted(todoDetails.isCompleted());

            Todo updatedTodo = todoRepository.save(todo);
            return ResponseEntity.ok(new ApiResponse(true, "Todo updated successfully", updatedTodo));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error updating todo with id: " + id, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTodo(@PathVariable Long id) {
        try {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

            todoRepository.delete(todo);
            return ResponseEntity.ok(new ApiResponse(true, "Todo deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error deleting todo with id: " + id, null));
        }
    }

}
