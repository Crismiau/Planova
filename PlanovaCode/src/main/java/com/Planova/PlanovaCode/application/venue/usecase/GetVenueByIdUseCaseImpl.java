package com.Planova.PlanovaCode.application.venue.usecase;

import com.Planova.PlanovaCode.domain.models.Venue;
import com.Planova.PlanovaCode.domain.ports.in.GetVenueByIdUseCase;
import com.Planova.PlanovaCode.domain.ports.out.VenueRepositoryPort;
import com.Planova.PlanovaCode.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVenueByIdUseCaseImpl implements GetVenueByIdUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public Venue getById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venue with id " + id + " not found"));
    }
}
