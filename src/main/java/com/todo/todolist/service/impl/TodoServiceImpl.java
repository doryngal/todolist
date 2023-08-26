package com.todo.todolist.service.impl;

import com.todo.todolist.model.ApiResponse;
import com.todo.todolist.model.Todo;
import com.todo.todolist.model.User;
import com.todo.todolist.repository.ResourceNotFoundException;
import com.todo.todolist.repository.TodoRepository;
import com.todo.todolist.service.TodoService;
import com.todo.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, UserService userService) {
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todos = todoRepository.findAll();
        log.info("Retrieved {} todos", todos.size());
        return todos;
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
    }

    @Override
    public ResponseEntity<ApiResponse> update(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);

        todo.setTitle(todoDetails.getTitle());
        todo.setCompleted(todoDetails.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);

        return ResponseEntity.ok(new ApiResponse(true, "Todo updated successfully", updatedTodo));
    }

    @Override
    public ResponseEntity<ApiResponse> deleteTodo(Long id) {
        Todo todo = getTodoById(id);

        todoRepository.delete(todo);
        return ResponseEntity.ok(new ApiResponse(true, "Todo deleted successfully", null));
    }

    @Override
    public List<Todo> getTodosByUserId(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    @Override
    public Todo createTodoForUser(Long userId, Todo todo) {
        User user = userService.findById(userId);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getTodosByUser(Long userId) {
        return todoRepository.findByUserId(userId);
    }
}
