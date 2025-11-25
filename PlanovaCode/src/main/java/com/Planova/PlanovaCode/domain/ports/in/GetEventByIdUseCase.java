package com.Planova.PlanovaCode.domain.ports.in;

import com.Planova.PlanovaCode.domain.models.Event;

public interface GetEventByIdUseCase {
    Event getById(Long id);


}
