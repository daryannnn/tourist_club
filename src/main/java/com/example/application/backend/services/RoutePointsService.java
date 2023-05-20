package com.example.application.backend.services;

import com.example.application.backend.models.RoutePoints;
import com.example.application.backend.repositories.RoutePointsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RoutePointsService implements CrudListener<RoutePoints> {
    private final RoutePointsRepository repository;

    @Override
    public Collection<RoutePoints> findAll() {
        return repository.findAll();
    }

    @Override
    public RoutePoints add(RoutePoints routePoints) {
        return repository.save(routePoints);
    }

    @Override
    public RoutePoints update(RoutePoints routePoints) {
        return repository.save(routePoints);
    }

    @Override
    public void delete(RoutePoints routePoints) {
        repository.delete(routePoints);
    }
}
