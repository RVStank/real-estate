package com.aacademy.realestate.converter;

import com.aacademy.realestate.dto.FloorDTO;
import com.aacademy.realestate.model.Floor;
import org.springframework.stereotype.Component;

@Component
public class FloorConverter {

    public FloorDTO toFloorDTO(Floor floor) {
        return FloorDTO.builder()
                .number(floor.getNumber())
                .id(floor.getId())
                .build();
    }

    public Floor toFloor(FloorDTO floorDTO) {
        return Floor.builder()
                .number(floorDTO.getNumber())
                .id(floorDTO.getId())
                .build();
    }
}
