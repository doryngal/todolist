package com.todo.todolist.service;


import com.todo.todolist.model.User;

import java.util.List;

public interface UserService {
    User register(User user);
    Long getId(String username);
    List<User> getAll();
    User findByUsername(String username);
    User findById(Long id);
    void deleteUser(Long id);
}