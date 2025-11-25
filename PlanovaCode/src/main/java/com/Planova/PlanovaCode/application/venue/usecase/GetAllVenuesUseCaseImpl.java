package com.Planova.PlanovaCode.application.venue.usecase;

import com.Planova.PlanovaCode.domain.models.Venue;
import com.Planova.PlanovaCode.domain.ports.in.GetAllVenuesUseCase;
import com.Planova.PlanovaCode.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetAllVenuesUseCaseImpl implements GetAllVenuesUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public Page<Venue> getAll(Pageable pageable) {
        return venueRepository.findAll(pageable);
    }
}
