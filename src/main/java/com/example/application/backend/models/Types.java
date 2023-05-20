package com.example.application.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Types")
@SequenceGenerator(name = "sequenceGe", initialValue = 6)
public class Types {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @GeneratedValue(generator = "sequenceGe")
    @NotNull
    private Integer id;

    @NotBlank
    private String name;
}
