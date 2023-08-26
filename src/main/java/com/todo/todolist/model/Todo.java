package com.todo.todolist.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;
import java.util.Set;
@Entity
@Table(name = "todos")
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
