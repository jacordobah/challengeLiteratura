package com.jalcoh.lieratura;

import com.jalcoh.lieratura.principal.Menu;
import com.jalcoh.lieratura.repository.AutorRepository;
import com.jalcoh.lieratura.repository.LibroRepository;
import com.jalcoh.lieratura.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LieraturaApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;

    public static void main(String[] args) {
		SpringApplication.run(LieraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        LibrosService librosService = new LibrosService(autorRepository, libroRepository);
		Menu menu= new Menu(librosService);
		menu.menuPrincipal();
	}
}
