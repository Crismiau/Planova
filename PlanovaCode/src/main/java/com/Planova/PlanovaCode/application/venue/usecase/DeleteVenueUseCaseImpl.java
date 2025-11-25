package com.Planova.PlanovaCode.application.venue.usecase;

import com.Planova.PlanovaCode.domain.ports.in.DeleteVenueUseCase;
import com.Planova.PlanovaCode.domain.ports.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    @Override
    public boolean delete(Long id) {
        if (venueRepository.findById(id).isEmpty()) {
            return false;
        }
        venueRepository.deleteById(id);
        return true;
    }
}
