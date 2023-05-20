package com.example.application.backend.services;

import com.example.application.backend.models.Hikes;
import com.example.application.backend.repositories.HikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class HikesService implements CrudListener<Hikes> {
    private final HikesRepository repository;
    @Override
    public Collection<Hikes> findAll() {
        return repository.findAll();
    }

    @Override
    public Hikes add(Hikes hikes) {
        return repository.save(hikes);
    }

    @Override
    public Hikes update(Hikes hikes) {
        return repository.save(hikes);
    }

    @Override
    public void delete(Hikes hikes) {
        repository.delete(hikes);
    }

    public Collection<Integer> findIdd() {
        return repository.findIdd();
    }
}
