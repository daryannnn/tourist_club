package com.example.application.backend.services;

import com.example.application.backend.models.TouristsToGroups;
import com.example.application.backend.repositories.TouristsToGroupsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TouristsToGroupsService implements CrudListener<TouristsToGroups> {
    private final TouristsToGroupsRepository repository;
    @Override
    public Collection<TouristsToGroups> findAll() {
        return repository.findAll();
    }

    @Override
    public TouristsToGroups add(TouristsToGroups touristsToGroups) {
        return repository.save(touristsToGroups);
    }

    @Override
    public TouristsToGroups update(TouristsToGroups touristsToGroups) {
        return repository.save(touristsToGroups);
    }

    @Override
    public void delete(TouristsToGroups touristsToGroups) {
        repository.delete(touristsToGroups);
    }
}
