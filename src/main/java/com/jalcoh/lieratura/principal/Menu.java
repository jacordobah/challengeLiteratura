package com.jalcoh.lieratura.principal;

import com.jalcoh.lieratura.modelos.Autor;
import com.jalcoh.lieratura.modelos.Idioma;
import com.jalcoh.lieratura.modelos.Libro;
import com.jalcoh.lieratura.repository.AutorRepository;
import com.jalcoh.lieratura.service.ConvierteDatos;
import com.jalcoh.lieratura.service.LibrosService;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    Scanner teclado = new Scanner(System.in);
    private LibrosService servicio;

    public Menu(LibrosService librosService){
        this.servicio = librosService;
    }

    public void menuPrincipal(){
        var opcion = -1;
        System.out.println("-----------------------");
        while (opcion != 0) {
            var menu = """
                    Elija la opción por el numero:
                    1 - Buscar libro por titulo
                    2 - Mostrar libros registrados
                    3 - Mostrar autores registrados
                    4 - Buscar autores vivos en un año determinado
                    5 - Mostrar libros por idioma
                    0 - Salir
                    """;
            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        servicio.buscarSerieWeb();
                        break;
                    case 2:
                        //buscarEpisodioPorSerie();
                        mostrarTodosLosLibros();
                        break;
                    case 3:
                        //mostrarSeriesBuscadas();
                        mostrarTodosLosAutores();
                        break;
                    case 4:
                        //buscar autores vivos;
                        System.out.println("Digite el año en el que desea consultar");
                        try{
                            Year anio= Year.parse(teclado.nextLine());
                            mostrarAutoresVivos(anio);
                        }catch (Exception e){
                            System.out.println("Dato de fecha invalido." + e);
                        }
                        break;
                    case 5:
                        menuIdiomas();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        return;
                    default:
                        System.out.println("Opción inválida");
                }
            }catch(Exception e) {
                System.out.println("opción invalida, verifique su entrada");
               // System.out.println(e.toString());
                teclado.nextLine();
            }

        }
    }


    public void menuIdiomas() {
        var opcion = "";
        var menu = """
                    ----------------
                    Ingrese el idioma por el cual buscar:
                    es - español
                    en - ingles
                    fr - frances
                    pt - portugues
                    de - aleman
                    otro - idioma difernte a los listados
                    """;
        System.out.println(menu);
        opcion = teclado.nextLine();
        if(Idioma.buscaCoincidencia(opcion)==null){
            System.out.println("""
                    El idioma que ingreso no es valido, puede que el texto se encuentre entre
                    los valores indefinidos de la base de idiomas guardados. Si busca un texto
                    en espcifico puede usar la opcion "indefinido" o intente buscar el libro 
                    con otra opción.
                    """);
        }else{
            List<Libro> librosRegistrados = servicio.buscarPorIdioma(Idioma.buscaCoincidencia(opcion));
            if(!librosRegistrados.isEmpty()){
                librosRegistrados.forEach(System.out::println);
            }else {
                System.out.println("No tenemos libros registrados aún en ese idioma");
            }
        }
        menuPrincipal();
    }

    private void mostrarTodosLosLibros() {
        List<Libro> librosRegistrados = servicio.mostrarTodosLosLibros();
        if (!librosRegistrados.isEmpty()){
            librosRegistrados.forEach(l-> System.out.println(l.toString()));
        }else{
            System.out.println("No hay aún libros registrados.");
        }
    }

    private void mostrarTodosLosAutores() {
        List<Autor> autoresRegistrados = servicio.mostrarTodosLosAutores();
        if (!autoresRegistrados.isEmpty()){
            System.out.println("--------Autores--------------\n");
            autoresRegistrados.forEach(a-> System.out.println(a.toString()));
            System.out.println("-*-*-*-*\n");
        }else{
            System.out.println("No hay aún autores registrados.");
        }
    }

    private void mostrarAutoresVivos(Year anio) {
        List<Autor> autoresVivos = servicio.mostrarAutoresVivos(anio);
        if(!autoresVivos.isEmpty()){
            autoresVivos.forEach(a-> System.out.println(a.toString()));
        }else{
            System.out.println("No se encontraron Autores vivos en ese año");
        }
    }



}
