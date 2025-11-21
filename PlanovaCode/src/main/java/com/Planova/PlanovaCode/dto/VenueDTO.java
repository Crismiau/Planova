// src/main/java/com/Planova/PlanovaCode/dto/VenueDTO.java
package com.Planova.PlanovaCode.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
// First HU1
@Data
public class VenueDTO {
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    private String direction;
}
