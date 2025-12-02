package com.Planova.PlanovaCode.validation;

import com.Planova.PlanovaCode.shared.dto.EventRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EventDateValidator implements ConstraintValidator<EventDateValid, EventRequestDTO> {

    @Override
    public boolean isValid(EventRequestDTO event, ConstraintValidatorContext context){
         if(event.getFechaInicio() == null || event.getFechaFin() == null) {
             return  true;
         }
         return event.getFechaInicio().isBefore(event.getFechaFin());
     }

}
