package com.aacademy.realestate.converter;

import com.aacademy.realestate.dto.*;
import com.aacademy.realestate.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EstateConverter {

    public Estate toEstate(EstateDTO estateDTO) {
        return Estate.builder()
                .id(estateDTO.getId())
                .city(City.builder()
                        .id(estateDTO.getCityId())
                        .build())
                .floor(Floor.builder()
                        .id(estateDTO.getFloorId())
                        .build())
                .description(estateDTO.getDescription())
                .quadrature(Quadrature.builder()
                        .pureArea(estateDTO.getPureArea())
                        .builtUpArea(estateDTO.getBuiltUpArea())
                        .build())
                .estateFeatures(estateDTO.getEstateFeatureIds().stream()
                        .map(estateFeatureId -> EstateFeature.builder()
                                .id(estateFeatureId)
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public EstateResponse toEstateResponse(Estate estate) {
        return EstateResponse.builder()
                .id(estate.getId())
                .builtUpArea(estate.getQuadrature().getBuiltUpArea().toString())
                .pureArea(estate.getQuadrature().getPureArea().toString())
                .cityDTO(CityDTO.builder()
                        .id(estate.getCity().getId())
                        .name(estate.getCity().getName())
                        .build())
                .floorDTO(FloorDTO.builder()
                        .id(estate.getFloor().getId())
                        .number(estate.getFloor().getNumber())
                        .build())
                .estateFeatureDTOS(estate.getEstateFeatures().stream().map(estateFeature ->
                        EstateFeatureDTO.builder()
                                .id(estateFeature.getId())
                                .feature(estateFeature.getFeature())
                                .build()).collect(Collectors.toSet()))
                .description(estate.getDescription())
                .build();
    }
}
