package com.example.application.backend.repositories;

import com.example.application.backend.models.Leaders;
import com.example.application.backend.models.Routes;
import com.example.application.backend.models.Tourists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoutesRepository extends JpaRepository<Routes, Integer> {
    @Query(value = """
            select r.*
            from "routes" as r
            where r."id" in (select "route" from "hikes" where "id" in (
                                select "hike" from "hikers" where "hiker"in (
                                    select "tourist" from "tourists_to_groups" where "grooup" in (
                                        select "id" from "groups" where "section" = (select id from sections where name = :sec)))))
            group by r."id"
            order by r."id" asc;""",
            nativeQuery = true)
    Set<Routes> findBySection(@Param("sec") String sec);

    @Query(value = """
            select r.*
            from "routes" as r
            where (select "start_date" from "hikes" where "route" = r."id" limit 1)
                    between :start and :end
            group by r."id"
            order by r."id" asc;""",
            nativeQuery = true)
    Set<Routes> findByTime(@Param("start")LocalDate start, @Param("end") LocalDate end);

    @Query(value = """
            select r.*
            from "routes" as r, "routes" as rc
            where r."id" in (select "route" from "hikes" where "instructor" = (select id
                                                                               from tourists
                                                                               where name = :name))
            group by r."id"
            order by r."id" asc;""",
            nativeQuery = true)
    Set<Routes> findByInstructor(@Param("name") String name);

    @Query(value = """
            select r.*
            from "routes" as r, "routes" as rc
            where :amount = (select count(*) from "hikes" where "route" = r."id")
            group by r."id"
            order by r."id" asc;""",
            nativeQuery = true)
    Set<Routes> findByAmount(@Param("amount") Integer amount);

    @Query(value = """
            select r.*
            from "routes" as r, "routes" as rc
            where r."id" in (select "route" from "route_points" where "point" = (
                                select "id" from "points" where "name" = :name))
            group by r."id"
            order by r."id" asc;""",
            nativeQuery = true)
    Set<Routes> findByPoint(@Param("name") String name);

    @Query(value = """
            select r.*
            from "routes" as r, "routes" as rc
            where r."length" > :length
            group by r."id"
            order by r."id" asc;""",
            nativeQuery = true)
    Set<Routes> findByLength(@Param("length") Integer length);

    @Query(value = """
            select r.*
            from "routes" as r, "routes" as rc
            where (select check_complexity(r."id") = :comp)
            or :comp in (select "complexity" from "hikes" where "route" = r."id")
            group by r."id"
            order by r."id" asc;""",
            nativeQuery = true)
    Set<Routes> findByComplexity(@Param("comp") Integer comp);

    List<Routes> findByNameContainingIgnoreCase(String name);

    @Query(value = """
            select name from routes;""",
            nativeQuery = true)
    Set<String> findNamed();

    @Query(value = """
            select name from routes where id=:type""",
            nativeQuery = true)
    String getRouteName(@Param("type") Integer type);

    @Query(value = """
            select id from routes where name = :name""",
            nativeQuery = true)
    Integer findIdByName(@Param("name") String name);
}
