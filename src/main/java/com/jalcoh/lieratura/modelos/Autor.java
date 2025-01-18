package com.jalcoh.lieratura.modelos;

import jakarta.persistence.*;
import org.springframework.core.style.ToStringCreator;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="autores")
public class Autor {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Year anoNacimiento;
    private Year anoFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    Autor(){}

    public Autor(String nombre, String anoNacimiento, String anoFallecimiento){
        this.nombre = nombre;
        this.anoNacimiento = Year.parse(anoNacimiento);
        this.anoFallecimiento= Year.parse(anoFallecimiento);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Year getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Year anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Year getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Year anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l-> l.setAutor(this));
        this.libros = libros;
    }

    @Override
    public String toString() {
        String librosAutor = this.libros.stream()
                .map(Libro::getTitulo).collect(Collectors.joining(", "));

        return "\n           ***                   " +
               "\n Autor: " + this.nombre +
               "\n Año nacimiento: " + this.anoNacimiento +
               "\n Año Fallecimiento: " + this.anoFallecimiento +
               "\n Libros: [" + librosAutor + "]\n";

    }

}
