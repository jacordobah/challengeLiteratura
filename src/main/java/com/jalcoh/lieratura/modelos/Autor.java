package com.jalcoh.lieratura.modelos;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

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


}
