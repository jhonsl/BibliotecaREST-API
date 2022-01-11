package com.softka.biblioteca;

import com.softka.biblioteca.services.implementation.ServiceRecursoImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Autowired
	private ServiceRecursoImp serviceRecursoImp;
	private static Logger log = LoggerFactory.getLogger(BibliotecaApplication.class);

	@Override
	public void run(String... args) throws Exception {

	}
}
