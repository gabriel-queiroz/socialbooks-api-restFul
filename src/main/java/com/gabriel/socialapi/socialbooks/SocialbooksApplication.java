package com.gabriel.socialapi.socialbooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.socialapi.socialbooks.repository.LivrosRepository;

@SpringBootApplication
public class SocialbooksApplication {

	@Autowired
	LivrosRepository livrosRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SocialbooksApplication.class, args);
	}
	
}
