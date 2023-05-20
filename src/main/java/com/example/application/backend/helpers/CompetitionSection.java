package com.example.application.backend.helpers;

import com.example.application.backend.models.Competitions;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompetitionSection {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private String name;
    private String section_name;
}
