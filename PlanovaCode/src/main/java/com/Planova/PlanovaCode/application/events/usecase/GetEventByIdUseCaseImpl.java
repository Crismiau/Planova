package com.Planova.PlanovaCode.application.events.usecase;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.in.GetEventByIdUseCase;
import com.Planova.PlanovaCode.domain.ports.out.EventRepositoryPort;
import com.Planova.PlanovaCode.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetEventByIdUseCaseImpl implements GetEventByIdUseCase {

    private final EventRepositoryPort eventRepository;

    @Override
    @Transactional(readOnly = true)
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event with id " + id + " not found"));
    }

}
