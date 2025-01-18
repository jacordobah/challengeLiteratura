package com.jalcoh.lieratura.repository;



import com.jalcoh.lieratura.modelos.DatosLibro;
import com.jalcoh.lieratura.modelos.Idioma;
import com.jalcoh.lieratura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long>{

    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdioma(Idioma categoria);
}
