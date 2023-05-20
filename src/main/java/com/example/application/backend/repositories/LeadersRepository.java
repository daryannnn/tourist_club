package com.example.application.backend.repositories;

import com.example.application.backend.models.Leaders;
import com.example.application.backend.models.Tourists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface LeadersRepository extends JpaRepository<Leaders, Integer> {
    @Query(value = """
            select l.*
            from "leaders" as l, "leaders" as lc
            where l."salary" = :salary
            group by l."id"
            order by l."id" asc;""",
            nativeQuery = true)
    Set<Leaders> findBySalary(@Param("salary") Integer salary);

    @Query(value = """
            select l.*
            from "leaders" as l, "leaders" as lc
            where date_part('year', l."birthday") = :year
            group by l."id"
            order by l."id" asc;""",
            nativeQuery = true)
    Set<Leaders> findByYear(@Param("year") Integer year);

    @Query(value = """
            select l.*
            from "leaders" as l
            where l."employment_year" = :year
            group by l."id"
            order by l."id" asc;""",
            nativeQuery = true)
    Set<Leaders> findByEmpYear(@Param("year") Integer year);

    @Query(value = """
            select l.*
            from "leaders" as l
            where date_part('year', age(l."birthday")) = :age
            group by l."id"
            order by l."id" asc;""",
            nativeQuery = true)
    Set<Leaders> findByAge(@Param("age") Integer age);

    List<Leaders> findByNameContainingIgnoreCase(String name);

    @Query(value = """
            select name from leaders order by name""",
            nativeQuery = true)
    Set<String> findNamed();

    @Query(value = """
            select id from leaders where name = :name""",
            nativeQuery = true)
    Integer findIdByName(@Param("name") String name);
}
