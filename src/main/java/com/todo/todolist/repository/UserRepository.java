package com.todo.todolist.repository;

import com.todo.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
//    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
