package com.example.application.backend.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CSService implements CrudListener<CompetitionSection> {
    private final CSRepo repo;

    @Override
    public Collection<CompetitionSection> findAll() {
        return repo.findAll();
    }

    @Override
    public CompetitionSection add(CompetitionSection competitionSection) {
        return repo.save(competitionSection);
    }

    @Override
    public CompetitionSection update(CompetitionSection competitionSection) {
        return repo.save(competitionSection);
    }

    @Override
    public void delete(CompetitionSection competitionSection) {
        repo.delete(competitionSection);
    }

    public Collection<CompetitionSection> findAllBySection() {
        return repo.findByAllSection();
    };
}
