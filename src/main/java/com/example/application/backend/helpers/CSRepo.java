package com.example.application.backend.helpers;

import com.example.application.backend.models.Coaches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CSRepo extends JpaRepository<CompetitionSection, Integer> {
    @Query(value = """
            select c."id", c."name", s."name" as "section_name"
            from "competitions" as c, "sections" as s
            where c."id" in (select "competition" from "competitors" where "competitor" in (
                    select "id" from "tourists" where "tourists"."id" in (select "tourist" from "tourists_to_groups" where
                        "grooup" in (select "id" from "groups" where "section" = s."id" ))
                ))
            group by c."id", s."name"
            order by c."id" asc;""",
            nativeQuery = true)
    Set<CompetitionSection> findByAllSection();
}
