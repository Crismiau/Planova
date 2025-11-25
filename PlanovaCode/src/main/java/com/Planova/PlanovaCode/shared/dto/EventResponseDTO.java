package com.Planova.PlanovaCode.shared.dto;

import lombok.Data;

/**
 * DTO de salida para exponer datos de un Event al exterior.
 * Se usa exclusivamente en los adaptadores de entrada/salida
 * (Controllers, APIs REST, etc.).
 */
@Data
public class EventResponseDTO {

    private Long id;
    private String name;
    private String description;
    private int capacity;
    private String venueName;

    // Si tu dominio tiene más campos:
    private String category;
    private String city;
    private String startDate; // como String (ISO8601) para evitar exponer LocalDateTime
}
