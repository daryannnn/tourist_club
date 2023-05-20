package com.example.application.backend.models;

import com.example.application.backend.keys.HikeDiariesKey;
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
@Table(name="Hike_diaries")
@IdClass(HikeDiariesKey.class)
public class HikeDiaries {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer hike;

    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer day;

    private String notes;
}
