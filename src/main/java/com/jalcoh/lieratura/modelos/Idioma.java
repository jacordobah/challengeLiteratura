package com.jalcoh.lieratura.modelos;

import java.util.List;

public enum Idioma {
    es("es"),
    en("en"),
    fr("fr"),
    pt("pt"),
    de("de"),
    indefinido("indefinido");

    private String categoria;

    Idioma(String categoria){
        this.categoria = categoria;
    }

    public static Idioma fromCodigo(List<String> codigos) {
       for (String codigo : codigos) {
           for (Idioma idioma : Idioma.values()) {
               if (idioma.toString().equalsIgnoreCase(codigo)) {
                   return idioma; // Retorna el primer valor que coincide.
               }
           }
       }
       return indefinido;
   }

    public static Idioma buscaCoincidencia(String codigo) {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.toString().equalsIgnoreCase(codigo)) {
                    return idioma; // Retorna el primer valor que coincide.
                }
            }
        return null;
    }

}
