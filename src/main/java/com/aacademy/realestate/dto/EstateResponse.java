package com.aacademy.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EstateResponse {

    private Long id;

    private String builtUpArea;

    private String pureArea;

    private String description;

    private CityDTO cityDTO;

    private FloorDTO floorDTO;

    private Set<EstateFeatureDTO> estateFeatureDTOS;
}
