package com.example.application.backend.services;

import com.example.application.backend.models.Sections;
import com.example.application.backend.models.Tourists;
import com.example.application.backend.repositories.SectionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionsService implements CrudListener<Sections> {
    private final SectionsRepository repository;

    @Override
    public Collection<Sections> findAll() {
        return repository.findAll();
    }

    @Override
    public Sections add(Sections sections) {
        return repository.save(sections);
    }

    @Override
    public Sections update(Sections sections) {
        return repository.save(sections);
    }

    @Override
    public void delete(Sections sections) {
        repository.delete(sections);
    }

    public List<Sections> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Collection<String> findNamed(){
        return repository.findNamed();
    }

    public String getTypeName(Integer type) {return repository.getTypeName(type);}

    public String getLeaderName(Integer type) {return repository.getLeaderName(type);}

    public String getSectionName(Integer type) {return repository.getSectionName(type);}

    public Integer findIdByName(String name) {
        return repository.findIdByName(name);
    }
}
