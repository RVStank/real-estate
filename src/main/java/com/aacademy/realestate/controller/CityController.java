package com.aacademy.realestate.controller;

import com.aacademy.realestate.converter.CityConverter;
import com.aacademy.realestate.dto.CityDTO;
import com.aacademy.realestate.model.City;
import com.aacademy.realestate.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    private final CityService cityService;
    private final CityConverter cityConverter;

    public CityController(CityService cityService, CityConverter cityConverter) {
        this.cityService = cityService;
        this.cityConverter = cityConverter;
    }

    @PostMapping
    public ResponseEntity<CityDTO> save(@RequestBody CityDTO cityDTO) {
        City city = cityConverter.toCity(cityDTO);
        City savedCity = cityService.save(city);
        CityDTO cityDTOResponse = cityConverter.toCityDTO(savedCity);
        return ResponseEntity.ok(cityDTOResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id) {
        City foundCity = cityService.findById(id);
        CityDTO cityDTO = cityConverter.toCityDTO(foundCity);
        return ResponseEntity.ok(cityDTO);
    }
}
