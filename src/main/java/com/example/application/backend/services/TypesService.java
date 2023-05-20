package com.example.application.backend.services;

import com.example.application.backend.models.Types;
import com.example.application.backend.repositories.TypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TypesService implements CrudListener<Types> {
    private final TypesRepository repository;

    @Override
    public Collection<Types> findAll() {
        return repository.findAll();
    }

    @Override
    public Types add(Types types) {
        return repository.save(types);
    }

    @Override
    public Types update(Types types) {
        return repository.save(types);
    }

    @Override
    public void delete(Types types) {
        repository.delete(types);
    }

    public Set<String> findNamed() {
        return repository.findNamed();
    }

    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }
}
