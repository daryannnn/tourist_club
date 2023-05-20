package com.example.application.backend.repositories;

import com.example.application.backend.models.Hikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface HikesRepository extends JpaRepository<Hikes, Integer> {
    @Query(value = """
            select id from hikes order by id;""",
            nativeQuery = true)
    Set<Integer> findIdd();

}
