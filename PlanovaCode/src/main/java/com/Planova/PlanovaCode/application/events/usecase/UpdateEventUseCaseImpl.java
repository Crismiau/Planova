package com.Planova.PlanovaCode.application.events.usecase;

import com.Planova.PlanovaCode.domain.models.Event;
import com.Planova.PlanovaCode.domain.ports.in.UpdateEventUseCase;
import com.Planova.PlanovaCode.domain.ports.out.EventRepositoryPort;
import com.Planova.PlanovaCode.exception.DuplicateResourceException;
import com.Planova.PlanovaCode.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
@RequiredArgsConstructor
@Service
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    private  final EventRepositoryPort eventRepositoryPort;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Event update(Long id, Event event){

        Event existing = eventRepositoryPort.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));

        if(!existing.getName().equalsIgnoreCase(event.getName())) {
            eventRepositoryPort.findByName(event.getName()).ifPresent(e -> {
                throw new DuplicateResourceException("Another event with this name already exists");

            });
        }
        existing.setName(event.getName());
        existing.setDescription(event.getDescription());
        existing.setCapacity(event.getCapacity());
        existing.setVenueName(event.getVenueName());
        existing.setCategory(event.getCategory());
        existing.setCity(event.getCity());
        existing.setStartDate(event.getStartDate());

        return eventRepositoryPort.save(existing);
    }
}
