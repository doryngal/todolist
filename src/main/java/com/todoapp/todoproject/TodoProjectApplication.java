package com.todoapp.todoproject;

import com.todoapp.todoproject.httpsserver.HttpServer;
import org.apache.catalina.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoProjectApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TodoProjectApplication.class, args);
		HttpServer.Server(args);
	}

}
