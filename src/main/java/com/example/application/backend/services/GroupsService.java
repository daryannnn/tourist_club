package com.example.application.backend.services;

import com.example.application.backend.models.Groups;
import com.example.application.backend.repositories.GroupsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupsService implements CrudListener<Groups> {
    private final GroupsRepository repository;
    @Override
    public Collection<Groups> findAll() {
        return repository.findAll();
    }

    @Override
    public Groups add(Groups groups) {
        return repository.save(groups);
    }

    @Override
    public Groups update(Groups groups) {
        return repository.save(groups);
    }

    @Override
    public void delete(Groups groups) {
        repository.delete(groups);
    }

    public Set<Integer> findIdd() {
        return repository.findIdd();
    }
}
