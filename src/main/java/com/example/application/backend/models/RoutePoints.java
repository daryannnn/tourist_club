package com.example.application.backend.models;

import com.example.application.backend.keys.RoutePointsKey;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Route_points")
@IdClass(RoutePointsKey.class)
public class RoutePoints {
    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer route;

    @EqualsAndHashCode.Include
    @jakarta.persistence.Id
    @NotNull
    private Integer point;

    @NotNull
    @Positive
    private Integer day;
}
