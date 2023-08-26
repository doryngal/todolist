package com.todo.todolist.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.todo.todolist.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto {
    private String username;
    private String firstname;
    private String lastname;
    private String password;

    private Long roleId;


    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(password);
        return user;
    }

    public RegisterDto toRegister(User user){
        RegisterDto dto = new RegisterDto();
        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setPassword(user.getPassword());
        return dto;
    }
}