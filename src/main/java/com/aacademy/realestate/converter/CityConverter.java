package com.aacademy.realestate.converter;

import com.aacademy.realestate.dto.CityDTO;
import com.aacademy.realestate.dto.NeighborhoodDTO;
import com.aacademy.realestate.model.City;
import com.aacademy.realestate.model.Neighborhood;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CityConverter {

    public CityDTO toCityDTO(City city) {
        return CityDTO.builder()
                .name(city.getName())
                .id(city.getId())
                .neighborhoodIds(city.getNeighborhoods()
                        .stream()
                        .map(Neighborhood::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public City toCity(CityDTO cityDTO) {
        return City.builder()
                .id(cityDTO.getId())
                .name(cityDTO.getName())
                .neighborhoods(cityDTO.getNeighborhoodIds()
                        .stream()
                        .map((neighborhoodId) ->
                                Neighborhood.builder().id(neighborhoodId).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
