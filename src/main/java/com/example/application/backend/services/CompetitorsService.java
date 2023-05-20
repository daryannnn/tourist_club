package com.example.application.backend.services;

import com.example.application.backend.models.Competitors;
import com.example.application.backend.repositories.CompetitorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CompetitorsService implements CrudListener<Competitors> {
    private final CompetitorsRepository repository;

    @Override
    public Collection<Competitors> findAll() {
        return repository.findAll();
    }

    @Override
    public Competitors add(Competitors competitors) {
        return repository.save(competitors);
    }

    @Override
    public Competitors update(Competitors competitors) {
        return repository.save(competitors);
    }

    @Override
    public void delete(Competitors competitors) {
        repository.delete(competitors);
    }
}
