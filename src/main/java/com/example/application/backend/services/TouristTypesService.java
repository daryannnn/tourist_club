package com.example.application.backend.services;

import com.example.application.backend.models.TouristTypes;
import com.example.application.backend.repositories.TouristTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TouristTypesService implements CrudListener<TouristTypes> {
    private final TouristTypesRepository repository;

    @Override
    public Collection<TouristTypes> findAll() {
        return repository.findAll();
    }

    @Override
    public TouristTypes add(TouristTypes touristTypes) {
        return repository.save(touristTypes);
    }

    @Override
    public TouristTypes update(TouristTypes touristTypes) {
        return repository.save(touristTypes);
    }

    @Override
    public void delete(TouristTypes touristTypes) {
        repository.delete(touristTypes);
    }

    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }

    public Set<String> findNamed() {
        return repository.findNamed();
    }
}
