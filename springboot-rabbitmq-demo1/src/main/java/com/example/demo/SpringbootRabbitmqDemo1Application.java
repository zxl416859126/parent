package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync //开启异步
public class SpringbootRabbitmqDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqDemo1Application.class, args);
	}

}
