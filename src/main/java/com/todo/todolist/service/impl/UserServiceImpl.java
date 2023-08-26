package com.todo.todolist.service.impl;

import com.todo.todolist.model.User;
import com.todo.todolist.repository.UserRepository;
import com.todo.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        User registeredUser = userRepository.save(user);
        log.info("User {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("{} users found", users.size());
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.info("No user found by username: {}", username);
        } else {
            log.info("User {} found by username: {}", user, username);
        }
        return user;
    }

    @Override
    public Long getId(String username) {
        User user = userRepository.findByUsername(username);
        return (user != null) ? user.getId() : null;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("No user found by id: {}", id);
        } else {
            log.info("User {} found by id: {}", user, id);
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("User with id: {} successfully deleted", id);
    }
}
