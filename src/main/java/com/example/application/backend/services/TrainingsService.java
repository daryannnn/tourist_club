package com.example.application.backend.services;

import com.example.application.backend.models.Tourists;
import com.example.application.backend.models.Trainings;
import com.example.application.backend.repositories.TrainingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingsService implements CrudListener<Trainings> {
    private final TrainingsRepository repository;

    @Override
    public Collection<Trainings> findAll() {
        return repository.findAll();
    }

    @Override
    public Trainings add(Trainings trainings) {
        return repository.save(trainings);
    }

    @Override
    public Trainings update(Trainings trainings) {
        return repository.save(trainings);
    }

    @Override
    public void delete(Trainings trainings) {
        repository.delete(trainings);
    }

    public List<Trainings> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Collection<String> findNamed() {
        return repository.findNamed();
    }

    public String getNameById(Integer type) {return repository.getNameById(type);}

    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }
}
