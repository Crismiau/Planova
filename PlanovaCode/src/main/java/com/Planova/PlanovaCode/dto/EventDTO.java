// src/main/java/com/Planova/PlanovaCode/dto/EventDTO.java
package com.Planova.PlanovaCode.dto;

import lombok.Data;
// First HU1
@Data
public class EventDTO {
    private long id;
    private String name;
    private String description;
    private int capacity;
    private String venueName;
}
