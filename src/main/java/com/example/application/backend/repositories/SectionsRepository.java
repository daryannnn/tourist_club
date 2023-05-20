package com.example.application.backend.repositories;

import com.example.application.backend.models.Sections;
import com.example.application.backend.models.Tourists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SectionsRepository extends JpaRepository<Sections, Integer> {
    List<Sections> findByNameContainingIgnoreCase(String name);

    @Query(value = """
            select name from sections;""",
            nativeQuery = true)
    Collection<String> findNamed();

    @Query(value = """
            select name from types where id=:type""",
            nativeQuery = true)
    String getTypeName(@Param("type") Integer type);

    @Query(value = """
            select name from leaders where id=:type""",
            nativeQuery = true)
    String getLeaderName(@Param("type") Integer type);

    @Query(value = """
            select name from sections where id=:type""",
            nativeQuery = true)
    String getSectionName(@Param("type") Integer type);

    @Query(value = """
            select id from sections where name = :name""",
            nativeQuery = true)
    Integer findIdByName(@Param("name") String name);
}
