package com.aacademy.realestate.controller;

import com.aacademy.realestate.converter.NeighborhoodConverter;
import com.aacademy.realestate.dto.NeighborhoodDTO;
import com.aacademy.realestate.service.NeighborhoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;
    private final NeighborhoodConverter neighborhoodConverter;

    public NeighborhoodController(NeighborhoodService neighborhoodService, NeighborhoodConverter neighborhoodConverter) {
        this.neighborhoodService = neighborhoodService;
        this.neighborhoodConverter = neighborhoodConverter;
    }

    @GetMapping
    public ResponseEntity<Set<NeighborhoodDTO>> findAll() {
        return ResponseEntity.ok(neighborhoodService.findAll()
                .stream()
                .map(neighborhoodConverter::toNeighborhoodDTO)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<NeighborhoodDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(neighborhoodConverter.toNeighborhoodDTO(neighborhoodService.findByName(name)));
    }

    @PostMapping
    public ResponseEntity<NeighborhoodDTO> save(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        return ResponseEntity.ok(neighborhoodConverter.toNeighborhoodDTO(
                neighborhoodService.save(
                        neighborhoodConverter.toNeighborhood(neighborhoodDTO))));
    }
}
