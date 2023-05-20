package com.example.application.backend.models;

import com.example.application.backend.keys.CampsAndStopsKey;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name="Camps_and_stops")
@IdClass(CampsAndStopsKey.class)
public class CampsAndStops {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer hike;

    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer day;

    @NotBlank
    private String camps;
    @NotBlank
    private String stops;
}
