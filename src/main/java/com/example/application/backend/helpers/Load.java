package com.example.application.backend.helpers;

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
public class Load {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private String name;
    private String training_name;
    private Float hours;
}
