package com.example.application.backend.services;

import com.example.application.backend.models.Coaches;
import com.example.application.backend.models.Tourists;
import com.example.application.backend.repositories.CoachesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CoachesService implements CrudListener<Coaches> {
    private final CoachesRepository repository;

    @Override
    public Collection<Coaches> findAll() {
        return repository.findAll();
    }

    @Override
    public Coaches add(Coaches coaches) {
        return repository.save(coaches);
    }

    @Override
    public Coaches update(Coaches coaches) {
        return repository.save(coaches);
    }

    @Override
    public void delete(Coaches coaches) {
        repository.delete(coaches);
    }

    public Collection<Tourists> findBySection(String name) {
        return repository.findBySection(name);
    }

    public Collection<Tourists> findBySex(String sex) {
        return repository.findBySex(sex);
    }

    public Collection<Tourists> findByAge(Integer age) {
        return repository.findByAge(age);
    }

    public Collection<Tourists> findBySalary(Integer salary) {
        return repository.findBySalary(salary);
    }

    public Collection<Tourists> findBySpec(String spec) {
        return repository.findBySpec(spec);
    }

    public Collection<Tourists> findAllCoaches() {
        return repository.findAllCoaches();
    }

    public Collection<String> findNamed() {return repository.findNamed();}
}
