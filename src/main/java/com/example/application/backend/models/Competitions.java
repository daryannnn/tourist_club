package com.example.application.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Competitions")
@SequenceGenerator(name = "s", initialValue = 14)
public class Competitions {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "s")
    private Integer id;

    @NotBlank
    private String name;
}
