package com.example.application.backend.repositories;

import com.example.application.backend.models.Tourists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface TouristRepository extends JpaRepository<Tourists, Integer> {
    @Query(value = """
            select t.* from "tourists" as t
                where t."id" in (select "tourist" from "tourists_to_groups" where
                                        "grooup" in (select "id" from "groups" where
                                                    "section" = (select "id" from "sections" where
                                                            "sections"."name" =  :name)))
                group by t."id"
                order by t."id" asc""",
    nativeQuery = true)
    Set<Tourists> findBySection(@Param("name") String name);

    @Query(value = """
            select t.*
                                       from "tourists" as t
                                       where t."id" in (select "tourist" from "tourists_to_groups" where
                                                               "grooup" = :id)
                                       group by t."id"
                                       order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByGroup(@Param("id") Integer id);

    @Query(value = """
             select t.*
                                       from "tourists" as t
                                       where t."sex" = :sex
                                       group by t."id"
                                       order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySex(@Param("sex") String sex);

    @Query(value = """
             select t.*
             from "tourists" as t, "tourists" as tc
             where date_part('year', t."birthday") = :year
             group by t."id"
             order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByYear(@Param("year") Integer year);

    @Query(value = """
             select t.*
                                     from "tourists" as t, "tourists" as tc
                                     where date_part('year', age(t."birthday")) = :age
                                     group by t."id"
                                     order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByAge(@Param("age") Integer age);

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
    Set<Tourists> findCoachBySection(@Param("name") String name);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."type" = (select "id" from "tourist_types" where "name" = 'Тренер')
            and t."sex" = :sex
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findCoachBySex(@Param("sex") String sex);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."type" = (select "id" from "tourist_types" where "name" = 'Тренер')
            and date_part('year', age(t."birthday")) = :age
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findCoachByAge(@Param("age") Integer age);

    @Query(value = """
            select t.*
            from "tourists" as t
            where t."type" = (select "id" from "tourist_types" where "name" = 'Тренер')
            and t."id" in (select "id" from "coaches" where "salary" = :salary)
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findCoachBySalary(@Param("salary") Integer salary);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "coach" from "groups" where "groups"."section" in (
                select "id" from "sections" where "sections"."type" = (select "id" from "types" where "name" = :spec)
                ))
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findCoachBySpec(@Param("spec") String spec);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in
                                    (select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and :amount = (select count(*) from "hikers" where "hiker" = t."id")
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySecAmount(@Param("sec") String sec, @Param("amount") Integer amount);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and :amount = (select count(*) from "hikers" where "hiker" = t."id")
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByGroupAmount(@Param("group") Integer group, @Param("amount") Integer amount);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and :hike in (select "hike" from "hikers" where "hiker" = t."id")
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByGroupHike(@Param("group") Integer group, @Param("hike") Integer hike);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in
                                    (select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and :hike in (select "hike" from "hikers" where "hiker" = t."id")
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySecHike(@Param("sec") String sec, @Param("hike") Integer hike);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in
                                    (select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and (select "start_date" from "hikes" where "id" in (
                    select "hike" from "hikers" where "hiker" = t."id") limit 1)
                between :start and :end
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySecTime(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("sec") String sec);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and (select "start_date" from "hikes" where "id" in (
                    select "hike" from "hikers" where "hiker" = t."id") limit 1)
                between :start and :end
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByGroupTime(@Param("start")LocalDate start, @Param("end") LocalDate end, @Param("group") Integer group);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in
                                    (select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and :route in (select name from routes where id in (select "route" from "hikes" where "id" in (select "hike" from "hikers" where "hiker" = t."id")))
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySecRoute(@Param("route")String route, @Param("sec") String sec);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and :route in (select name from routes where id in (select "route" from "hikes" where "id" in (select "hike" from "hikers" where "hiker" = t."id")))
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByGroupRoute(@Param("route")String route, @Param("group") Integer group);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in
                                    (select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and :point in (select "name" from "points" where "id" in (
                                        select "point" from "route_points" where "route" in (
                                            select "route" from "hikes" where "id" in (
                                                select "hike" from "hikers" where "hiker" = t."id"))))
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySecPoint(@Param("point")String point, @Param("sec") String sec);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and :point in (select "name" from "points" where "id" in (
                                        select "point" from "route_points" where "route" in (
                                            select "route" from "hikes" where "id" in (
                                                select "hike" from "hikers" where "hiker" = t."id"))))
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByGroupPoint(@Param("point")String point, @Param("group") Integer group);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and t."id" in (select "id" from "tourists" where "category" = :cat)
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByGroupCat(@Param("cat")Integer cat, @Param("group") Integer group);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in
                                    (select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and t."id" in (select "id" from "tourists" where "category" = :cat)
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySecCat(@Param("cat")Integer cat, @Param("sec") String sec);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in (
                                select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in (
                                select "id" from "groups" where "section" in (
                                    select "id" from "sections" where "type" = (
                                        select "id" from "types" where "name" = :type))))
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findBySecType(@Param("type") String type, @Param("sec") String sec);

    @Query(value = """
            select t.*
            from "tourists" as t, "tourists" as tc
            where t."id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and t."id" in (select "tourist" from "tourists_to_groups" where "grooup" in (
                                select "id" from "groups" where "section" in (
                                    select "id" from "sections" where "type" = (
                                        select "id" from "types" where "name" = :type))))
            group by t."id"
            order by t."id" asc;""",
            nativeQuery = true)
    Set<Tourists> findByGroupType(@Param("type") String type, @Param("group") Integer group);

    @Query(value = """
            select *
            from "tourists" as t
            where "id" in (select "hiker" from "hikers" where "hike" in (
                            select "id" from "hikes" where "instructor" in (
                                select "coach" from "groups" where "id" in (
                                    select "grooup" from "tourists_to_groups" where "tourist" = t."id")
                                and "section" = (select id from sections where name = :sec))))
            group by t."id";""",
            nativeQuery = true)
    Set<Tourists> findBySecOwnCoach(@Param("sec") String sec);

    @Query(value = """
            select *
            from "tourists" as t
            where "id" in (select "hiker" from "hikers" where "hike" in (
                            select "id" from "hikes" where "instructor" in (
                                select "coach" from "groups" where "id" in (
                                    select "grooup" from "tourists_to_groups" where "tourist" = t."id")
                                and "id" = :group)))
            group by t."id";""",
            nativeQuery = true)
    Set<Tourists> findByGroupOwnCoach(@Param("group") Integer group);

    @Query(value = """
            select *
            from "tourists" as t
            where "id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and 1 = ((select check_hiker_in_routes(t."id", :routes, :size)))
            group by t."id";""",
            nativeQuery = true)
    Set<Tourists> findByGroupRoute13(@Param("group") Integer group, @Param("routes") String[] routes, @Param("size") Integer size);

    @Query(value = """
            select *
            from "tourists" as t
            where "id" in (select "tourist" from "tourists_to_groups" where "grooup" in (select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and 1 = ((select check_hiker_in_routes(t."id", :routes, :size)))
            group by t."id";""",
            nativeQuery = true)
    Set<Tourists> findBySecRoute13(@Param("sec") String sec, @Param("routes") String[] routes, @Param("size") Integer size);

    @Query(value = """
            select *
            from "tourists" as t
            where "id" in (select "tourist" from "tourists_to_groups" where "grooup" = :group)
            and 1 = (select check_hiker_in_all(t."id"))
            group by t."id\"""",
            nativeQuery = true)
    Set<Tourists> findByGroupRouteAll(@Param("group") Integer group);

    @Query(value = """
            select *
            from "tourists" as t
            where "id" in (select "tourist" from "tourists_to_groups" where "grooup" in (select "id" from "groups" where "section" = (select id from sections where name = :sec)))
            and 1 = (select check_hiker_in_all(t."id"))
            group by t."id";""",
            nativeQuery = true)
    Set<Tourists> findBySecRouteAll(@Param("sec") String sec);

    List<Tourists> findByNameContainingIgnoreCase(String name);

    @Query(value = """
            select * from tourists where (id in (select "instructor"
            from "hikes"
            where "instructor" in (select "id" from "tourists" where "category" = :cat)
            group by "instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInCat(@Param("cat") Integer cat);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h."instructor" in (select "id" from "tourists" where "category" = :cat and "type" = 2)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInSpCat(@Param("cat") Integer cat);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h."instructor" in (select "id" from "tourists" where "category" = :cat and "type" = 3)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInCoCat(@Param("cat") Integer cat);

    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where :amount = (select count(*) from "hikes" where "instructor" = h."instructor")
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInAmount(@Param("amount") Integer amount);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where :amount = (select count(*) from "hikes" where "instructor" = h."instructor")
            and h."instructor" in (select "id" from "tourists" where "type" = 2)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInSpAmount(@Param("amount") Integer amount);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where :amount = (select count(*) from "hikes" where "instructor" = h."instructor")
            and h."instructor" in (select "id" from "tourists" where "type" = 3)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInCoAmount(@Param("amount") Integer amount);

    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h.id = :hike
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInHike(@Param("hike") Integer hike);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where :hike = h.id
            and h."instructor" in (select "id" from "tourists" where "type" = 2)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInSpHike(@Param("hike") Integer hike);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where :hike = h.id
            and h."instructor" in (select "id" from "tourists" where "type" = 3)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInCoHike(@Param("hike") Integer hike);

    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h."id" in (select "id" from "hikes" where "route" = (select id from routes where name=:route))
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInRoute(@Param("route") String route);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h."id" in (select "id" from "hikes" where "route" = (select id from routes where name=:route))
            and h."instructor" in (select "id" from "tourists" where "type" = 2)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInSpRoute(@Param("route") String route);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h."id" in (select "id" from "hikes" where "route" = (select id from routes where name=:route))
            and h."instructor" in (select "id" from "tourists" where "type" = 3)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInCoRoute(@Param("route") String route);

    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h."id" in (select "id" from "hikes" where "route" in (
                                            select "route" from "route_points" where "point" in (
                                                select "id" from "points" where "name" = :point)))
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInPoint(@Param("point") String point);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h."id" in (select "id" from "hikes" where "route" in (
                                            select "route" from "route_points" where "point" in (
                                                select "id" from "points" where "name" = :point)))
            and h."instructor" in (select "id" from "tourists" where "type" = 2)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInSpPoint(@Param("point") String point);
    @Query(value = """
            select * from tourists where (id in (select h."instructor"
            from "hikes" as h
            where h."id" in (select "id" from "hikes" where "route" in (
                                            select "route" from "route_points" where "point" in (
                                                select "id" from "points" where "name" = :point)))
            and h."instructor" in (select "id" from "tourists" where "type" = 3)
            group by h."instructor"));""",
            nativeQuery = true)
    Set<Tourists> findByInCoPoint(@Param("point") String point);

    @Query(value = """
            select name from tourists where id in (select instructor from hikes)""",
            nativeQuery = true)
    Set<String> findNamedInstructors();


    @Query(value = """
            select name from tourist_types where id=:type""",
            nativeQuery = true)
    String getTypeName(@Param("type") Integer type);

    @Query(value = """
            select name from tourists where id=:type""",
            nativeQuery = true)
    String getNameById(@Param("type") Integer type);

    @Query(value = """
            select id from tourists where name=:name""",
            nativeQuery = true)
    Integer getIdByName(@Param("name") String name);

    @Query(value = """
            select name from tourists;""",
            nativeQuery = true)
    Set<String> findNamed();

    @Query(value = """
            select name from tourists where (type = 2 or type = 3);""",
            nativeQuery = true)
    Set<String> findNamedNotLover();
}
