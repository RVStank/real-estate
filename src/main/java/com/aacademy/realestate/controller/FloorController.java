package com.aacademy.realestate.controller;

import com.aacademy.realestate.converter.FloorConverter;
import com.aacademy.realestate.dto.FloorDTO;
import com.aacademy.realestate.model.Floor;
import com.aacademy.realestate.service.FloorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/floors")
public class FloorController {

    private final FloorService floorService;
    private final FloorConverter floorConverter;

    @Autowired
    public FloorController(FloorService floorService, FloorConverter floorConverter) {
        this.floorService = floorService;
        this.floorConverter = floorConverter;
    }

    @GetMapping
    public ResponseEntity<Set<FloorDTO>> findAll() {
        return ResponseEntity.ok(floorService.findAll()
                .stream()
                .map(floorConverter::toFloorDTO)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<FloorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(floorConverter.toFloorDTO(floorService.findById(id)));
    }

    @GetMapping(value = "/number/{number}")
    public ResponseEntity<FloorDTO> findByNumber(@PathVariable Integer number) {
        return ResponseEntity.ok(floorConverter.toFloorDTO(floorService.findByNumber(number)));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FloorDTO> update(@RequestBody @Valid FloorDTO floorDTO, @PathVariable Long id) {
        Floor floor = floorConverter.toFloor(floorDTO);
        Floor updatedFloor = floorService.update(floor, id);
        return ResponseEntity.ok(floorConverter.toFloorDTO(updatedFloor));
    }

    @PostMapping
    public ResponseEntity<FloorDTO> save(@RequestBody @Valid FloorDTO floorDTO) {
        Floor floor = floorConverter.toFloor(floorDTO);
        Floor savedFloor = floorService.save(floor);
        return ResponseEntity.ok(floorConverter.toFloorDTO(savedFloor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        floorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
