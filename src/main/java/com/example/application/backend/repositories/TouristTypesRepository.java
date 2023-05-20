package com.example.application.backend.repositories;

import com.example.application.backend.models.TouristTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TouristTypesRepository extends JpaRepository<TouristTypes, Integer> {
    @Query(value = """
            select id from tourist_types where name = :name""",
            nativeQuery = true)
    Integer findIdByName(@Param("name") String name);

    @Query(value = """
            select name from tourist_types""",
            nativeQuery = true)
    Set<String> findNamed();
}
