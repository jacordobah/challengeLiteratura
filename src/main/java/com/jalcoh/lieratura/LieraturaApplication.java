package com.jalcoh.lieratura;

import com.jalcoh.lieratura.principal.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LieraturaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LieraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu= new Menu();
		menu.menuPrincipal();
	}
}
