package com.jalcoh.lieratura.service;


import com.jalcoh.lieratura.modelos.*;
import com.jalcoh.lieratura.repository.AutorRepository;
import com.jalcoh.lieratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibrosService {

    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;
    Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    @Autowired
    public LibrosService(AutorRepository autorR, LibroRepository libroR){
        this.autorRepository = autorR;
        this.libroRepository = libroR;
    }

    public void buscarSerieWeb() {
        ConsumoAPI consumoApi = new ConsumoAPI();
        System.out.println("Ingrese el nombre del libro: ");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+ "?search=" + tituloLibro.replace(" ","%20"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.libros().stream()
                .filter(l-> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            //si libro no existe
            Optional<Libro> libroEnDB = libroRepository.findByTituloContainsIgnoreCase(datosBusqueda.libros().get(0).titulo());

            if(libroEnDB.isEmpty()){
                //System.out.println("guardando libro");
                Optional<DatosAutor> primerAutor = libroBuscado.stream()
                        .map(da -> da.autor().get(0)).findFirst().stream().findFirst();
                //System.out.println("datos autor: " + primerAutor.get());
                Libro libroEncontrado = new Libro(libroBuscado.get());
                //busca si el autor existe
                Optional<Autor> autorEnDB = autorRepository.findByNombreContainsIgnoreCase(primerAutor.get().nombre());
                if(autorEnDB.isEmpty()){
                    Autor autor= new Autor(primerAutor.get().nombre(),primerAutor.get().fechaDeNacimiento(),
                            primerAutor.get().añoFallecimiento());
                    List<Libro> libros = new ArrayList<>();
                    libros.add(libroEncontrado);
                    autor.setLibros(libros);
                    autorRepository.save(autor);
                }else{
                    //System.out.println("refrescando autor");
                    Autor a = autorEnDB.get();
                    //libroEncontrado.setAutorPorId(a.getId());
                    libroEncontrado.setAutor(a);
                    libroRepository.save(libroEncontrado);
                }
            }else{
                System.out.println("NO se puede registrar el libro mas de una vez");
            }
            //System.out.println(libroBuscado.get());
        }else{
            System.out.println("Libro no encontrado");
        }
    }

    public List<Libro> mostrarTodosLosLibros() {
        return libroRepository.findAll();

    }

    public List<Autor> mostrarTodosLosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> mostrarAutoresVivos(Year anio) {
        return autorRepository.encontrarVivos(anio);
    }

    public List<Libro> buscarPorIdioma(Idioma categoria) {
        return libroRepository.findByIdioma(categoria);
    }
}
