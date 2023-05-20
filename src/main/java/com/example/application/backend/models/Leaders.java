package com.example.application.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Leaders")
@SequenceGenerator(name = "sequ", initialValue = 7)
public class Leaders {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @GeneratedValue(generator = "sequ")
    private Integer Id;

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private Integer salary;

    @NotNull
    @Min(value = 1950, message = "Год принятия на работу не может быть меньше 1950")
    private Integer employment_year;

    @NotNull
    @Past
    private LocalDate birthday;
}
