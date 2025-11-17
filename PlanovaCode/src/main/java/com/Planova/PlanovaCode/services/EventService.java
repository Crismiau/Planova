package com.Planova.PlanovaCode.services;

import com.Planova.PlanovaCode.dto.EventCreationDTO;
import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.dto.VanueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final VanueService vanueService;
    private static final List<EventDTO> eventos = new ArrayList<>();
    private static long nextId  = 1;

    @Autowired
    public EventService(VanueService vanueService){
        this.vanueService = vanueService;
    }

    // Método de creación que recibe el DTO de INPUT (CreationDTO)
    public EventDTO create(EventCreationDTO creationDTO) {

        // --- PASO CLAVE: BÚSQUEDA DEL ID ---
        VanueDTO venue = vanueService.findByName(creationDTO.getVenueName());

        // 1. Manejo de Errores (para Tarea #3: aquí se lanzaría una excepción 400)
        if (venue == null) {
            // Por ahora, para que compile y sigamos, lanzamos un error básico.
            // En Tarea #3, lo haremos con @ResponseStatus.
            throw new IllegalArgumentException("Venue not found with name: " + creationDTO.getVenueName());
        }


        // 2. Conversión a EventDTO (el objeto interno) y Asignación de ID
        EventDTO newEvent = new EventDTO();
        newEvent.setId(nextId++); // Asigna un nuevo ID de Evento
        newEvent.setName(creationDTO.getName());
        newEvent.setDescription(creationDTO.getDescription());
        newEvent.setId(venue.getId()); // Asigna el ID del Venue encontrado
        // ... set other fields

        // 3. Guardar en la lista en memoria
        eventos.add(newEvent);

        return newEvent; // Devuelve el objeto guardado
    }



}
