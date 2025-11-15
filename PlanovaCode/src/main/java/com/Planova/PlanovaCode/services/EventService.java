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
    private static final long nextId  = 1;

    @Autowired
    public EventService(VanueService vanueService){
        this.vanueService = vanueService;
    }

    public EventDTO createEvent(EventCreationDTO eventCreationDTO){

        VanueDTO vanue = vanueService.findByName(eventCreationDTO.getVenueName());
    }





}
