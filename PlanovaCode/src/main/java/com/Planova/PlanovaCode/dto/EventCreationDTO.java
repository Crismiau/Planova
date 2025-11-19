// src/main/java/com/Planova/PlanovaCode/dto/EventCreationDTO.java
package com.Planova.PlanovaCode.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
// First HU1
@Data
public class EventCreationDTO {

    @NotBlank(message = "name is required")
    private String name;

    private String description;

    @Min(value = 0, message = "capacity must be >= 0")
    private int capacity;

    @NotBlank(message = "venueName is required")
    private String venueName;
}
