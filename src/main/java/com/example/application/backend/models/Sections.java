package com.example.application.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name="Sections")
@SequenceGenerator(name = "sequen", initialValue = 14)
public class Sections {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "sequen")
    private Integer id;

    @NotBlank
    private String name;


    private Integer leader;

    @NotNull
    @Min(value = 1, message = "Тип должен быть от 1 до 5")
    @Max(value = 5, message = "Тип должен быть от 1 до 5")
    private Integer type;
}
