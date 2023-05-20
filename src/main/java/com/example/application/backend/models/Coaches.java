package com.example.application.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="Coaches")
public class Coaches {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    @NotNull
    private Integer section;
    @NotNull
    private Integer salary;
}
