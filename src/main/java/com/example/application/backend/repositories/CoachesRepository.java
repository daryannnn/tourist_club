package com.example.application.backend.repositories;

import com.example.application.backend.models.Coaches;
import com.example.application.backend.models.Tourists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CoachesRepository extends JpaRepository<Coaches, Integer> {
    @Query(value = """
            select t.*
            from "tourists" as t, "sections" as s
            where t."id" in (select "coach" from "groups" where "groups"."section" = s."id")
            group by t."id"
            order by t."id" asc""",
            nativeQuery = true)
    Set<Tourists> findAllCoaches();

    @Query(value = """
            select t.*
            from "tourists" as t
            where "id" in (select "coach" from "groups" where "groups"."section" =
                                    (select "id" from "sections" where "name" = :name))""",
            nativeQuery = true)
    Set<Tourists> findBySection(@Param("name") String name);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."type" = (select "id" from "tourist_types" where "name" = 'Тренер')
            and t."sex" = :sex
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySex(@Param("sex") String sex);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."type" = (select "id" from "tourist_types" where "name" = 'Тренер')
            and date_part('year', age(t."birthday")) = :age
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByAge(@Param("age") Integer age);

    @Query(value = """
            select t.*
            from "tourists" as t
            where t."type" = (select "id" from "tourist_types" where "name" = 'Тренер')
            and t."id" in (select "id" from "coaches" where "salary" = :salary)
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySalary(@Param("salary") Integer salary);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "coach" from "groups" where "groups"."section" in (
                select "id" from "sections" where "sections"."type" = (select "id" from "types" where "name" = :spec)
                ))
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySpec(@Param("spec") String spec);

    @Query(value = """
            select name from tourists where type = 3;""",
            nativeQuery = true)
    Set<String> findNamed();
}
