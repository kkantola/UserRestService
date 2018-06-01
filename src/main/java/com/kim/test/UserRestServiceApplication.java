package com.kim.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.goalkeeper.hcb.security"}) 
public class UserRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRestServiceApplication.class, args);
	}
}
