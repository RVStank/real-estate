package com.aacademy.realestate.dto;

import com.aacademy.realestate.model.Quadrature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EstateDTO {

    private Long id;

    private String description;

    private BigDecimal pureArea;

    private BigDecimal builtUpArea;

    private Long floorId;

    private Long cityId;

    private Set<Long> estateFeatureIds;
}
