package com.example.application.backend.helpers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Set;

public interface LoadRepo extends JpaRepository<Load, Integer> {
    @Query(value = """
            select "tourists"."id", "tourists"."name", tr."name" as "training_name", ((sum(tr."duration"))*1.0)/120 as "hours"
            from "tourists", "trainings" as tr
            where "type" = 3
            and tr."date" between '2023-02-20' and '2023-03-05'
            and "tourists"."id" in (select "coach" from "groups" where "id" in (select "grooup" from "trainings" where "id" = tr."id"))
            group by "tourists"."id", tr."name";""",
            nativeQuery = true)
    Set<Load> findAllLoads();

    @Query(value = """
            select "tourists"."id", "tourists"."name", tr."name" as "training_name", ((sum(tr."duration"))*1.0)/120 as "hours"
            from "tourists", "trainings" as tr
            where "type" = 3
            and tr."date" between '2023-02-20' and '2023-03-05'
            and "tourists"."id" in (select "coach" from "groups" where "id" in (select "grooup" from "trainings" where "id" = tr."id"))
            and tr."name" = :name
            group by "tourists"."id", tr."name"
            order by "tourists"."id" asc;""",
            nativeQuery = true)
    Set<Load> findByTraining(@Param("name") String name);

    @Query(value = """
            select "tourists"."id", "tourists"."name", tr."name" as "training_name", ((sum(tr."duration"))*1.0)/60 as "hours"
            from "tourists", "trainings" as tr
            where "type" = 3
            and tr."date" between :start and :end
            and "tourists"."id" in (select "coach" from "groups" where "id" in (select "grooup" from "trainings" where "id" = tr."id"))
            and "tourists"."name" = :name
            group by "tourists"."id", tr.name
            order by "tourists"."id" asc;""",
            nativeQuery = true)
    Set<Load> findByCoachPeriod(@Param("start")LocalDate start, @Param("end") LocalDate end, @Param("name") String name);

    @Query(value = """
            select "sections"."id", "sections"."name", tr."name" as "training_name", ((sum(tr."duration"))*1.0)/60 as "hours"
            from "sections", "trainings" as tr
            where "sections"."id" = (select id from sections where name = :name)
            and tr."date" between :start and :end
            and tr."grooup" in (select "id" from "groups" where "section" = (select id from sections where name = :name))
            group by "sections"."id","sections"."name", tr.name;""",
            nativeQuery = true)
    Set<Load> findBySecPeriod(@Param("start")LocalDate start, @Param("end") LocalDate end, @Param("name") String name);
}
