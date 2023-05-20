package com.example.application.backend.services;

import com.example.application.backend.models.HikeDiaries;
import com.example.application.backend.repositories.HikeDiariesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class HikeDiariesService implements CrudListener<HikeDiaries> {
    private final HikeDiariesRepository repository;

    @Override
    public Collection<HikeDiaries> findAll() {
        return repository.findAll();
    }

    @Override
    public HikeDiaries add(HikeDiaries hikeDiaries) {
        return repository.save(hikeDiaries);
    }

    @Override
    public HikeDiaries update(HikeDiaries hikeDiaries) {
        return repository.save(hikeDiaries);
    }

    @Override
    public void delete(HikeDiaries hikeDiaries) {
        repository.delete(hikeDiaries);
    }
}
