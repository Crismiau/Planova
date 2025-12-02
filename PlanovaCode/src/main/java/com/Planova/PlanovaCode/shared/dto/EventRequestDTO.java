package com.Planova.PlanovaCode.shared.dto;

import java.time.LocalDateTime;

import com.Planova.PlanovaCode.validation.EventDateValid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@EventDateValid // <-- ANOTACIÓN CUSTOM
public class EventRequestDTO {

    @NotBlank(message = "{event.name.notBlank}")
    private String name;

    @NotNull(message = "{event.start.notNull}")
    private LocalDateTime fechaInicio;

    @NotNull(message = "{event.end.notNull}")
    private LocalDateTime fechaFin;

    private String description;
    private Integer capacity;
    private String venueName; // Changed from venueId
    private String category;
    private String city;
    private LocalDateTime startDate;
    // getters y setters
}

