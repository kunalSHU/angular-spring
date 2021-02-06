package com.example.kunal.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ActiveProfiles({"dev"})
@EnableSwagger2
public class SpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

}
