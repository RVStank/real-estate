package com.aacademy.realestate.controller;

import com.aacademy.realestate.converter.EstateConverter;
import com.aacademy.realestate.dto.EstateDTO;
import com.aacademy.realestate.dto.EstateResponse;
import com.aacademy.realestate.model.Estate;
import com.aacademy.realestate.service.EstateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estates")
@AllArgsConstructor
public class EstateController {

    private final EstateService estateService;
    private final EstateConverter estateConverter;

    @PostMapping
    public ResponseEntity<EstateResponse> save(@RequestBody @Valid EstateDTO estateDTO) {
        Estate estate = estateConverter.toEstate(estateDTO);
        Estate savedEstate = estateService.save(estate);
        return ResponseEntity.ok(estateConverter.toEstateResponse(savedEstate));
    }

    @GetMapping
    public ResponseEntity <Set<EstateResponse>> findAll() {
        return ResponseEntity.ok(estateService.findAll()
                .stream()
                .map(estateConverter::toEstateResponse)
                .collect(Collectors.toSet()));
    }
}
