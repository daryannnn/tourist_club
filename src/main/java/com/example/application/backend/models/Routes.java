package com.example.application.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Routes")
@SequenceGenerator(name = "seque", initialValue = 18)
public class Routes {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @GeneratedValue(generator = "seque")
    private Integer id;

    @NotNull
    @Positive
    private Integer days;

    @NotBlank
    private String name;

    @PositiveOrZero
    @NotNull
    private Integer length;
}
