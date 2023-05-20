package com.example.application.backend.services;

import com.example.application.backend.models.Routes;
import com.example.application.backend.models.Tourists;
import com.example.application.backend.repositories.RoutesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutesService implements CrudListener<Routes> {
    private final RoutesRepository repository;
    @Override
    public Collection<Routes> findAll() {
        return repository.findAll();
    }

    @Override
    public Routes add(Routes routes) {
        return repository.save(routes);
    }

    @Override
    public Routes update(Routes routes) {
        return repository.save(routes);
    }

    @Override
    public void delete(Routes routes) {
        repository.delete(routes);
    }

    public Collection<Routes> findBySection(String sec) {
        return repository.findBySection(sec);
    }
    public Collection<Routes> findByAmount(Integer amount) {
        return repository.findByAmount(amount);
    }
    public Collection<Routes> findByInstructor(String i) {
        return repository.findByInstructor(i);
    }
    public Collection<Routes> findByPeriod(LocalDate start, LocalDate end) {
        return repository.findByTime(start, end);
    }

    public Collection<Routes> findByPoint(String name) {
        return repository.findByPoint(name);
    }

    public Collection<Routes> findByLength(Integer l) {
        return repository.findByLength(l);
    }

    public Collection<Routes> findByComplexity(Integer c) {
        return repository.findByComplexity(c);
    }

    public List<Routes> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Collection<String> findNamed() {
        return repository.findNamed();
    }

    public String getRouteName(Integer type) {return repository.getRouteName(type);}

    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }
}
