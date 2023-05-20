package com.example.application.backend.services;

import com.example.application.backend.helpers.CompetitionSection;
import com.example.application.backend.models.Competitions;
import com.example.application.backend.models.Tourists;
import com.example.application.backend.repositories.CompetitionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompetitionsService implements CrudListener<Competitions> {
    private final CompetitionsRepository repository;
    @Override
    public Collection<Competitions> findAll() {
        return repository.findAll();
    }

    @Override
    public Competitions add(Competitions competitions) {
        return repository.save(competitions);
    }

    @Override
    public Competitions update(Competitions competitions) {
        return repository.save(competitions);
    }

    @Override
    public void delete(Competitions competitions) {
        repository.delete(competitions);
    }

    public Collection<Competitions> findBySection(String name) {
        return repository.findBySection(name);
    };

    public Collection<CompetitionSection> findAllBySection() {
        return repository.findByAllSection();
    };

    public List<Competitions> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public String getNameById(Integer type) {return repository.getNameById(type);}

    public Set<String> findNamed() {
        return repository.findNamed();
    }

    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }
}
