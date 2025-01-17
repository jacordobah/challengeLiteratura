package com.jalcoh.lieratura.principal;

import com.jalcoh.lieratura.modelos.Datos;
import com.jalcoh.lieratura.modelos.DatosLibro;
import com.jalcoh.lieratura.service.ConsumoAPI;
import com.jalcoh.lieratura.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    Scanner teclado = new Scanner(System.in);

    public void menuPrincipal(){
        var opcion = -1;
        System.out.println("-----------------------");
        while (opcion != 0) {
            var menu = """
                    Elija la poscion por el numero:
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
                        buscarSerieWeb();
                        break;
                    case 2:
                        //buscarEpisodioPorSerie();
                        break;
                    case 3:
                        //mostrarSeriesBuscadas();
                        break;
                    case 4:
                        //buscarSeriePorTitulo();
                        break;
                    case 5:
                        menuIdiomas();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }catch(Exception e){
                System.out.println("opción invalida, verifique su elección");
                opcion = -1;
            }


        }


    }
    public void menuIdiomas() {
        var opcion = "";
        var menu = """
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
        teclado.nextLine();

        switch (opcion) {
            case "es":
                buscarSerieWeb();
                break;
            case "en":
                //buscarEpisodioPorSerie();
                break;
            case "fr":
                //mostrarSeriesBuscadas();
                break;
            case "pt":
                break;
            case "de":
                break;
            case "otro":
                break;
            default:
                //notificación
                break;
        }

    }

    private void buscarSerieWeb() {
        ConsumoAPI consumoApi = new ConsumoAPI();
        System.out.println("Ingrese el nombre del libro: ");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+ "?search=" + tituloLibro.replace(" ","%20"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.libros().stream()
                .filter(l-> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
        }else{
            System.out.println("Libro no encontrado");
        }
    }


}
