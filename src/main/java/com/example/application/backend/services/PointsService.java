package com.example.application.backend.services;

import com.example.application.backend.models.Leaders;
import com.example.application.backend.models.Points;
import com.example.application.backend.models.Tourists;
import com.example.application.backend.repositories.PointsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointsService implements CrudListener<Points> {
    private final PointsRepository repository;

    @Override
    public Collection<Points> findAll() {
        return repository.findAll();
    }

    @Override
    public Points add(Points points) {
        return repository.save(points);
    }

    @Override
    public Points update(Points points) {
        return repository.save(points);
    }

    @Override
    public void delete(Points points) {
        repository.delete(points);
    }

    public List<Points> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Collection<String> findNamed() {
        return repository.findNamed();
    }

    public String getPointName(Integer type) {return repository.getPointName(type);}

    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }
}
