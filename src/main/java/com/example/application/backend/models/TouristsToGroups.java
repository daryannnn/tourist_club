package com.example.application.backend.models;

import com.example.application.backend.keys.TouristsToGroupsKey;
import com.example.application.backend.services.GroupsService;
import com.example.application.backend.services.TouristService;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
@Table(name="Tourists_to_groups")
@IdClass(TouristsToGroupsKey.class)
public class TouristsToGroups {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer tourist;

    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer grooup;
}
