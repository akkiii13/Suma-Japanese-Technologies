package com.sjtev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SjtevBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SjtevBackendApplication.class, args);
	}

}
