// src/main/java/com/Planova/PlanovaCode/controller/VenueController.java
package com.Planova.PlanovaCode.controller;

import com.Planova.PlanovaCode.dto.VenueDTO;
import com.Planova.PlanovaCode.services.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
// First HU1
@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService service;

    public VenueController(VenueService service) {
        this.service = service;
    }

    @Operation(summary = "Create venue")
    @PostMapping
    public ResponseEntity<VenueDTO> create(@Valid @RequestBody VenueDTO dto) {
        VenueDTO saved = service.create(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Get all venues")
    @GetMapping
    public ResponseEntity<List<VenueDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Get venue by id")
    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> getById(@PathVariable long id) {
        var v = service.findById(id);
        if (v == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(v);
    }

    @Operation(summary = "Update venue")
    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> update(@PathVariable long id, @Valid @RequestBody VenueDTO dto) {
        var updated = service.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete venue")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = service.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
