package com.example.application.backend.models;

import com.example.application.backend.keys.CompetitorsKey;
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
@Table(name="Competitors")
@IdClass(CompetitorsKey.class)
public class Competitors {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer competition;

    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer competitor;
}
