package com.example.application.backend.repositories;

import com.example.application.backend.models.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TypesRepository extends JpaRepository<Types, Integer> {
    @Query(value = """
            select name from types""",
            nativeQuery = true)
    Set<String> findNamed();

    @Query(value = """
            select id from types where name = :name""",
            nativeQuery = true)
    Integer findIdByName(@Param("name") String name);
}
