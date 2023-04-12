package com.aacademy.realestate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "estate_features")
public class EstateFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 3, max = 30, message = "Length should be between 3 and 30.")
    @Column(nullable = false)
    private String feature;

    @ManyToMany(mappedBy = "estateFeatures")
    Set<Estate> estates;
}
