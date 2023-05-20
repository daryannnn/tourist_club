package com.example.application.backend.repositories;

import com.example.application.backend.models.Tourists;
import com.example.application.backend.models.Trainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TrainingsRepository extends JpaRepository<Trainings, Integer> {
    List<Trainings> findByNameContainingIgnoreCase(String name);

    @Query(value = """
            select name from trainings group by name;""",
            nativeQuery = true)
    Collection<String> findNamed();

    @Query(value = """
            select name from trainings where id=:type""",
            nativeQuery = true)
    String getNameById(@Param("type") Integer type);

    @Query(value = """
            select id from trainings where name = :name""",
            nativeQuery = true)
    Integer findIdByName(@Param("name") String name);
}
