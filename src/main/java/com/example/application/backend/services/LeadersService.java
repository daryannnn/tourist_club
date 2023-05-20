package com.example.application.backend.services;

import com.example.application.backend.models.Leaders;
import com.example.application.backend.models.Tourists;
import com.example.application.backend.repositories.LeadersRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LeadersService implements CrudListener<Leaders> {
    private final LeadersRepository repository;

    @Override
    public Collection<Leaders> findAll() {
        return repository.findAll();
    }

    @Override
    public Leaders add(Leaders leaders) {
        return repository.save(leaders);
    }

    @Override
    public Leaders update(Leaders leaders) {
        return repository.save(leaders);
    }

    @Override
    public void delete(Leaders leaders) {
        repository.delete(leaders);
    }

    public Collection<Leaders> findBySalary(Integer salary) {
        return  repository.findBySalary(salary);
    }

    public Collection<Leaders> findByAge(Integer age) {
        return  repository.findByAge(age);
    }

    public Collection<Leaders> findByEmpYear(Integer year) {
        return  repository.findByEmpYear(year);
    }

    public Collection<Leaders> findByYear(Integer year) {
        return  repository.findByYear(year);
    }

    public List<Leaders> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Set<String> findNamed() {
        return repository.findNamed();
    }

    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }
}
