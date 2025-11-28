package com.Planova.PlanovaCode.application.events.usecase;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.in.GetAllEventsUseCase;
import com.Planova.PlanovaCode.domain.ports.out.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetAllEventsUseCaseImpl implements GetAllEventsUseCase {

    private final EventRepositoryPort eventRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Event> getAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }
}