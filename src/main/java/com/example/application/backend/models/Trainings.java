package com.example.application.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Trainings")
@SequenceGenerator(name = "sequenceG", initialValue = 1831)
public class Trainings {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @GeneratedValue(generator = "sequenceG")
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer grooup;

    @NotBlank
    private String place;

    @NotNull
    private LocalTime start_time;

    @NotNull
    @PositiveOrZero
    private Integer duration;

    private String description;

    //private String start_time;
}
