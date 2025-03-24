package com.roque.agenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(maxAge = 3600)
@SpringBootApplication
public class AgendaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}
}
