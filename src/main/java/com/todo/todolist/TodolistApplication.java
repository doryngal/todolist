package com.todo.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import java.io.IOException;



@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
