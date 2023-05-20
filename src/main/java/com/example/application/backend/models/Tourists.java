package com.example.application.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Tourists")
@SequenceGenerator(name = "sequenc", initialValue = 45)
public class Tourists {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "sequenc")
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp="ж|м", message = "Пол должен быть в формате м или ж")
    private String sex;

    @Min(value = 1, message = "Тип должен быть от 1 до 3")
    @Max(value = 3, message = "Тип должен быть от 1 до 3")
    @NotNull
    private Integer type;

    @Min(value = 0, message = "Категория должна быть от 0 до 4")
    @Max(value = 4, message = "Категория должна быть от 0 до 4")
    @NotNull
    private Integer category;

    private String notes;

    @NotNull
    @Past
    private LocalDate birthday;
}
