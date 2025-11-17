package com.Planova.PlanovaCode.controller;


import com.Planova.PlanovaCode.dto.EventCreationDTO;
import com.Planova.PlanovaCode.dto.EventDTO;
import com.Planova.PlanovaCode.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvento(@RequestBody EventCreationDTO creationDTO) {
        try{
            EventDTO newEvent  =eventService.create(creationDTO);

            return new ResponseEntity<>(newEvent, HttpStatus.CREATED);

        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
