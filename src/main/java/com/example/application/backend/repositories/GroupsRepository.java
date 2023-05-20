package com.example.application.backend.repositories;

import com.example.application.backend.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface GroupsRepository extends JpaRepository<Groups, Integer> {
    @Query(value = """
            select id from groups order by id;""",
            nativeQuery = true)
    Set<Integer> findIdd();

}
