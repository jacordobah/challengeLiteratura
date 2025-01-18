package com.jalcoh.lieratura.dto;

import com.jalcoh.lieratura.modelos.Idioma;

public record LibroDTO(
        String titulo,
        Idioma idioma,
        Long numeroDescargas
) {
}
