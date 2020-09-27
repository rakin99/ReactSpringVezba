package com.example.bioskop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.example.bioskop.model")
@EnableJpaRepositories("com.example.bioskop.repository")
@ComponentScan(basePackages = {"com.example.bioskop.config","com.example.bioskop.auth","com.example.bioskop.security","com.example.bioskop.controller","com.example.bioskop.service","com.example.bioskop.repository"})
@SpringBootApplication
public class BioskopApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BioskopApplication.class, args);
	}

}
