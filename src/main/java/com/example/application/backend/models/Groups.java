package com.example.application.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name="Groups")
@SequenceGenerator(name = "se", initialValue = 18)
public class Groups {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @GeneratedValue(generator = "se")
    private Integer id;

    @NotNull
    private Integer coach;
    @NotNull
    private Integer section;
}
