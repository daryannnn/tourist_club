package com.example.application.backend.helpers;

import com.vaadin.flow.server.communication.rpc.StringToNumberDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.time.LocalDate;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class LoadService implements CrudListener<Load> {
    private final LoadRepo repo;

    @Override
    public Collection<Load> findAll() {
        return repo.findAll();
    }

    @Override
    public Load add(Load load) {
        return repo.save(load);
    }

    @Override
    public Load update(Load load) {
        return repo.save(load);
    }

    @Override
    public void delete(Load load) {
        repo.delete(load);
    }

    public Collection<Load> findAllLoads() {
        return repo.findAllLoads();
    }

    public Collection<Load> findByTraining(String name) {
        return repo.findByTraining(name);
    }

    public Collection<Load> findByCoachPeriod(LocalDate start, LocalDate end, String name) {
        return repo.findByCoachPeriod(start, end, name);
    }

    public Collection<Load> findBySecPeriod(LocalDate start, LocalDate end, String name) {
        return repo.findBySecPeriod(start, end, name);
    }
}
