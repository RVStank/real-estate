package com.aacademy.realestate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Embeddable
public class Quadrature {

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal builtUpArea;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal pureArea;
}
