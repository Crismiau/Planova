package com.Planova.PlanovaCode.domain.ports.in;

import com.Planova.PlanovaCode.domain.models.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetAllVenuesUseCase {
    Page<Venue> getAll(Pageable pageable);
}
