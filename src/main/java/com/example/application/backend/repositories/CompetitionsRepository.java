package com.example.application.backend.repositories;

import com.example.application.backend.helpers.CompetitionSection;
import com.example.application.backend.models.Competitions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CompetitionsRepository extends JpaRepository<Competitions, Integer> {
    @Query(value = """
            select c.*
            from "competitions" as c
            where c."id" in (select "competition" from "competitors" where "competitor" in (
                    select "id" from "tourists" where "tourists"."id" in (select "tourist" from "tourists_to_groups" where
                        "grooup" in (select "id" from "groups" where "section" =(select "id" from "sections" where "name" = :name) ))
                ))
            group by c."id"
            order by c."id" asc;""",
            nativeQuery = true)
    Set<Competitions> findBySection(@Param("name") String name);

    @Query(value = """
            select c.*, s."name" as "sectionName"
            from "competitions" as c, "sections" as s
            where c."id" in (select "competition" from "competitors" where "competitor" in (
                    select "id" from "tourists" where "tourists"."id" in (select "tourist" from "tourists_to_groups" where
                        "grooup" in (select "id" from "groups" where "section" = s."id" ))
                ))
            group by c."id", s."name"
            order by c."id" asc;""",
            nativeQuery = true)
    Set<CompetitionSection> findByAllSection();

    List<Competitions> findByNameContainingIgnoreCase(String name);

    @Query(value = """
            select name from competitions where id=:type""",
            nativeQuery = true)
    String getNameById(@Param("type") Integer type);

    @Query(value = """
            select name from competitions""",
            nativeQuery = true)
    Set<String> findNamed();

    @Query(value = """
            select id from competitions where name = :name""",
            nativeQuery = true)
    Integer findIdByName(@Param("name") String name);
}
