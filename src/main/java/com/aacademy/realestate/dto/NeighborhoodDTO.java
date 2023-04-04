package com.aacademy.realestate.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class NeighborhoodDTO {

    private Long id;

    private String name;

    @Range(min = -10, max = 300, message = "Floor must be between -10 and 300.")
    private Integer number;
}

