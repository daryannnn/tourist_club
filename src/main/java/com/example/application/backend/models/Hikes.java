package com.example.application.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Hikes")
@SequenceGenerator(name = "seq", initialValue = 19)
public class Hikes {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @GeneratedValue(generator = "seq")
    private Integer id;

    @NotNull
    private Integer route;

    @NotNull
    private Integer instructor;

    @NotNull
    @Min(value = 1, message = "Тип должен быть от 1 до 5")
    @Max(value = 5, message = "Тип должен быть от 1 до 5")
    private Integer type;

    @NotNull
    @Min(value = 1, message = "Сложность должна быть от 1 до 4")
    @Max(value = 4, message = "Сложность должна быть от 1 до 4")
    private Integer complexity;

    private boolean planned;

    @NotNull
    private LocalDate start_date;
}
