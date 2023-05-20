package com.example.application.backend.services;

import com.example.application.backend.models.Hikers;
import com.example.application.backend.repositories.HikersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class HikersService implements CrudListener<Hikers> {
    private final HikersRepository repository;
    @Override
    public Collection<Hikers> findAll() {
        return repository.findAll();
    }

    @Override
    public Hikers add(Hikers hikers) {
        return repository.save(hikers);
    }

    @Override
    public Hikers update(Hikers hikers) {
        return repository.save(hikers);
    }

    @Override
    public void delete(Hikers hikers) {
        repository.delete(hikers);
    }
}
