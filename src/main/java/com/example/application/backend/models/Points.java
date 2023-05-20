package com.example.application.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Points")
@SequenceGenerator(name = "gen", initialValue = 18)
public class Points {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @GeneratedValue(generator = "gen")
    private Integer id;

    @NotBlank
    private String name;

    private String description;

    private String coordinates;
}
