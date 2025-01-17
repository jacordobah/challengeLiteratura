package com.jalcoh.lieratura.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    private Idioma idioma;
    private Long numeroDescargas;

    Libro(){}
    Libro(DatosLibro datosLibro){
        this.titulo= datosLibro.titulo();


    }
}
