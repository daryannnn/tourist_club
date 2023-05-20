package com.example.application.backend.repositories;

import com.example.application.backend.models.Hikes;
import com.example.application.backend.models.Points;
import com.example.application.backend.models.Tourists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PointsRepository extends JpaRepository<Points, Integer> {
    List<Points> findByNameContainingIgnoreCase(String name);

    @Query(value = """
            select name from points;""",
            nativeQuery = true)
    Set<String> findNamed();

    @Query(value = """
            select name from points where id=:type""",
            nativeQuery = true)
    String getPointName(@Param("type") Integer type);

    @Query(value = """
            select id from points where name=:name""",
            nativeQuery = true)
    Integer findIdByName(@Param("name") String name);

}
