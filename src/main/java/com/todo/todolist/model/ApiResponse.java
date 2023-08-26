package com.todo.todolist.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ApiResponse {
    @Column(name = "success")
    private boolean success;
    @Column(name = "message")
    private String message;
    @Column(name = "data")
    private Object data;

    public ApiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
