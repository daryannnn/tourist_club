package com.example.application.backend.services;

import com.example.application.backend.models.CampsAndStops;
import com.example.application.backend.repositories.CampsAndStopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CampsAndStopsService implements CrudListener<CampsAndStops> {
    private final CampsAndStopsRepository repository;

    @Override
    public Collection<CampsAndStops> findAll() {
        return repository.findAll();
    }

    @Override
    public CampsAndStops add(CampsAndStops campsAndStops) {
        return repository.save(campsAndStops);
    }

    @Override
    public CampsAndStops update(CampsAndStops campsAndStops) {
        return repository.save(campsAndStops);
    }

    @Override
    public void delete(CampsAndStops campsAndStops) {
        repository.delete(campsAndStops);
    }
}
