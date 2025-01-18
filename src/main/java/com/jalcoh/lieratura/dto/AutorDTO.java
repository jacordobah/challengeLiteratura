package com.jalcoh.lieratura.dto;

import java.time.Year;

public record AutorDTO(
        Long id,
        String nombre,
        Year anoNacimiento,
        Year anoFallecimiento
) {
}
