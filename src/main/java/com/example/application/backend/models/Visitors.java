package com.example.application.backend.models;

import com.example.application.backend.keys.VisitorsKey;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
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
@Table(name="Visitors")
@IdClass(VisitorsKey.class)
public class Visitors {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer visitor;

    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer training;
}
