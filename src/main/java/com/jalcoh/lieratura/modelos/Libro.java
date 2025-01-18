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
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Long numeroDescargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo= datosLibro.titulo();
        this.idioma= Idioma.fromCodigo(datosLibro.idioma());
        this.numeroDescargas= datosLibro.numeroDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setAutorPorId(Long id){
        this.autor.setId(id);
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "\n----------LIBRO------------" +
                "\n Titulo: " + this.titulo +
                "\n Autor: " + this.getAutor().getNombre() +
                "\n Idioma: " + this.idioma.toString() +
                "\n Numero de descargas: " + this.numeroDescargas +
                "\n---------------------------\n";
    }
}
