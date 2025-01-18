package com.jalcoh.lieratura.repository;

import com.jalcoh.lieratura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.time.Year;
import java.util.List;
import java.util.Optional;


public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN anoNacimiento AND anoFallecimiento")
    List<Autor> encontrarVivos(Year anio);
}
