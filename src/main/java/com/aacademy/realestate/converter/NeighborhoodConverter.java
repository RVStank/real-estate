package com.aacademy.realestate.converter;

import com.aacademy.realestate.dto.NeighborhoodDTO;
import com.aacademy.realestate.model.Neighborhood;
import org.springframework.stereotype.Component;

@Component
public class NeighborhoodConverter {

    public NeighborhoodDTO toNeighborhoodDTO(Neighborhood neighborhood) {
        return NeighborhoodDTO.builder()
                .name(neighborhood.getName())
                .id(neighborhood.getId())
                .build();
    }

    public Neighborhood toNeighborhood(NeighborhoodDTO neighborhoodDTO) {
        return Neighborhood.builder()
                .name(neighborhoodDTO.getName())
                .id(neighborhoodDTO.getId())
                .build();
    }
}
