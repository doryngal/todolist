package com.todo.todolist.controller;

import com.todo.todolist.dto.AuthenticationRequestDto;
import com.todo.todolist.dto.RegisterDto;
import com.todo.todolist.model.User;
import com.todo.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authUser(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            User user = userService.findByUsername(username);
            Long userId = userService.getId(username);
            currentUserId.set(userId);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("username", username);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto) {
        if (userService.findByUsername(dto.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        User user = dto.toUser();
        userService.register(user);
        return ResponseEntity.ok("Registration successful!");
    }

    @GetMapping("/getUsernameById/{id}")
    public ResponseEntity<String> getUsernameById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getUsername());
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> result = userService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    public Long getCurrentUserId() {
        return currentUserId.get();
    }
}
