package com.Planova.PlanovaCode.infrastructure.adapters.in.web;

import com.Planova.PlanovaCode.domain.models.Venue;
import com.Planova.PlanovaCode.domain.ports.in.*;
import com.Planova.PlanovaCode.infrastructure.mapper.VenueDtoMapper;
import com.Planova.PlanovaCode.shared.dto.VenueRequestDTO;
import com.Planova.PlanovaCode.shared.dto.VenueResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final CreateVenueUseCase createUseCase;
    private final GetAllVenuesUseCase getAllUseCase;
    private final GetVenueByIdUseCase getByIdUseCase;
    private final UpdateVenueUseCase updateUseCase;
    private final DeleteVenueUseCase deleteUseCase;
    private final VenueDtoMapper dtoMapper;

    @Operation(summary = "Create venue")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Venue created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Venue name already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VenueResponseDTO create(@RequestBody VenueRequestDTO req) {
        Venue domain = dtoMapper.toDomain(req);
        Venue created = createUseCase.create(domain);
        return dtoMapper.toResponse(created);
    }

    @Operation(summary = "Get all venues")
    @ApiResponse(responseCode = "200", description = "List of venues returned successfully")
    @GetMapping
    public Page<VenueResponseDTO> getAll(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Venue> venuePage = getAllUseCase.getAll(pageable);
        return venuePage.map(dtoMapper::toResponse);
    }

    @Operation(summary = "Get venue by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venue found"),
            @ApiResponse(responseCode = "404", description = "Venue not found")
    })
    @GetMapping("/{id}")
    public VenueResponseDTO getById(@PathVariable Long id) {
        Venue venue = getByIdUseCase.getById(id);
        return dtoMapper.toResponse(venue);
    }

    @Operation(summary = "Update venue")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venue updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Venue not found"),
            @ApiResponse(responseCode = "409", description = "Duplicated venue name")
    })
    @PutMapping("/{id}")
    public VenueResponseDTO update(@PathVariable Long id, @RequestBody VenueRequestDTO req) {
        Venue domain = dtoMapper.toDomain(req);
        Venue updated = updateUseCase.update(id, domain);
        return dtoMapper.toResponse(updated);
    }

    @Operation(summary = "Delete venue")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Venue deleted"),
            @ApiResponse(responseCode = "404", description = "Venue not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = deleteUseCase.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
