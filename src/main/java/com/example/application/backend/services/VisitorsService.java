package com.example.application.backend.services;

import com.example.application.backend.models.Visitors;
import com.example.application.backend.repositories.VisitorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class VisitorsService implements CrudListener<Visitors> {
    private final VisitorsRepository repository;

    @Override
    public Collection<Visitors> findAll() {
        return repository.findAll();
    }

    @Override
    public Visitors add(Visitors visitors) {
        return repository.save(visitors);
    }

    @Override
    public Visitors update(Visitors visitors) {
        return repository.save(visitors);
    }

    @Override
    public void delete(Visitors visitors) {
        repository.delete(visitors);
    }
}
