package com.example.application.backend.services;

import com.example.application.backend.models.Tourists;
import com.example.application.backend.repositories.TouristRepository;
import com.vaadin.flow.data.provider.DataProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.sql.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TouristService implements CrudListener<Tourists> {
    private final TouristRepository repository;

    @Override
    public Collection<Tourists> findAll() {
        return repository.findAll();
    }

    @Override
    public Tourists add(Tourists tourist) {
        return repository.save(tourist);
    }

    @Override
    public Tourists update(Tourists tourist) {
        return repository.save(tourist);
    }

    @Override
    public void delete(Tourists tourist) {
        repository.delete(tourist);
    }

    public Collection<Tourists> findBySection(String name) {
        return repository.findBySection(name);
    }

    public Collection<Tourists> findByGroup(Integer id) {
        return repository.findByGroup(id);
    }

    public Collection<Tourists> findBySex(String sex) {
        return repository.findBySex(sex);
    }

    public Collection<Tourists> findByYear(Integer year) {
        return repository.findByYear(year);
    }

    public Collection<Tourists> findByAge(Integer age) {
        return repository.findByAge(age);
    }

    public Collection<Tourists> findCoachBySection(String name) {
        return repository.findCoachBySection(name);
    }

    public Collection<Tourists> findCoachBySex(String sex) {
        return repository.findCoachBySex(sex);
    }

    public Collection<Tourists> findCoachByAge(Integer age) {
        return repository.findCoachByAge(age);
    }

    public Collection<Tourists> findCoachBySalary(Integer salary) {
        return repository.findCoachBySalary(salary);
    }

    public Collection<Tourists> findCoachBySpec(String spec) {
        return repository.findCoachBySpec(spec);
    }

    public Collection<Tourists> findAllCoaches() {
        return repository.findAllCoaches();
    }

    public Collection<Tourists> findBySecAmount(String sec, Integer amount) {
        return repository.findBySecAmount(sec, amount);
    }

    public Collection<Tourists> findByGroupAmount(Integer group, Integer amount) {
        return repository.findByGroupAmount(group, amount);
    }

    public Collection<Tourists> findByGroupHike(Integer group, Integer hike) {
        return repository.findByGroupHike(group, hike);
    }

    public Collection<Tourists> findBySecHike(String sec, Integer hike) {
        return repository.findBySecHike(sec, hike);
    }

    public Collection<Tourists> findBySecTime(LocalDate start, LocalDate end, String sec) {
        return repository.findBySecTime(start, end, sec);
    }

    public Collection<Tourists> findByGroupTime(LocalDate start, LocalDate end, Integer group) {
        return repository.findByGroupTime(start, end, group);
    }

    public Collection<Tourists> findBySecRoute(String route, String sec) {
        return repository.findBySecRoute(route, sec);
    }

    public Collection<Tourists> findByGroupRoute(String route, Integer group) {
        return repository.findByGroupRoute(route, group);
    }

    public Collection<Tourists> findBySecPoint(String point, String sec) {
        return repository.findBySecPoint(point, sec);
    }

    public Collection<Tourists> findByGroupPoint(String point, Integer group) {
        return repository.findByGroupPoint(point, group);
    }

    public Collection<Tourists> findByGroupCat(Integer cat, Integer group) {
        return repository.findByGroupCat(cat, group);
    }

    public Collection<Tourists> findBySecCat(Integer cat, String sec) {
        return repository.findBySecCat(cat, sec);
    }

    public Collection<Tourists> findBySecType(String type, String sec) {
        return repository.findBySecType(type, sec);
    }

    public Collection<Tourists> findByGroupType(String type, Integer group) {
        return repository.findByGroupType(type, group);
    }

    public Collection<Tourists> findByGroupOwnCoach(Integer group) {
        return repository.findByGroupOwnCoach(group);
    }

    public Collection<Tourists> findBySecOwnCoach(String sec) {
        return repository.findBySecOwnCoach(sec);
    }

    public Collection<Tourists> findBySecR13(String sec, String[] r, Integer s) {
        return repository.findBySecRoute13(sec, r, s);
    }

    public Collection<Tourists> findByGR13(Integer g, String[] r, Integer s) {
        return repository.findByGroupRoute13(g, r, s);
    }

    public Collection<Tourists> findBySecAllR(String sec) {
        return repository.findBySecRouteAll(sec);
    }

    public Collection<Tourists> findByGrAllR(Integer group) {
        return repository.findByGroupRouteAll(group);
    }

    public List<Tourists> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Collection<Tourists> findByInCat(Integer cat) {
        return repository.findByInCat(cat);
    }
    public Collection<Tourists> findByInSpCat(Integer cat) {
        return repository.findByInSpCat(cat);
    }
    public Collection<Tourists> findByInCoCat(Integer cat) {
        return repository.findByInCoCat(cat);
    }

    public Collection<Tourists> findByInAmount(Integer cat) {
        return repository.findByInAmount(cat);
    }
    public Collection<Tourists> findByInSpAmount(Integer cat) {
        return repository.findByInSpAmount(cat);
    }
    public Collection<Tourists> findByInCoAmount(Integer cat) {
        return repository.findByInCoAmount(cat);
    }

    public Collection<Tourists> findByInHike(Integer cat) {
        return repository.findByInHike(cat);
    }
    public Collection<Tourists> findByInSpHike(Integer cat) {
        return repository.findByInSpHike(cat);
    }
    public Collection<Tourists> findByInCoHike(Integer cat) {
        return repository.findByInCoHike(cat);
    }

    public Collection<Tourists> findByInRoute(String route) {
        return repository.findByInRoute(route);
    }
    public Collection<Tourists> findByInSpRoute(String route) {
        return repository.findByInSpRoute(route);
    }
    public Collection<Tourists> findByInCoRoute(String route) {
        return repository.findByInCoRoute(route);
    }

    public Collection<Tourists> findByInPoint(String route) {
        return repository.findByInPoint(route);
    }
    public Collection<Tourists> findByInSpPoint(String route) {
        return repository.findByInSpPoint(route);
    }
    public Collection<Tourists> findByInCoPoint(String route) {
        return repository.findByInCoPoint(route);
    }

    public Collection<String> findNamedInstructors() {return repository.findNamedInstructors();}

    public String getTypeName(Integer type) {return repository.getTypeName(type);}

    public String getNameById(Integer type) {return repository.getNameById(type);}

    public Integer getIdByName(String type) {return repository.getIdByName(type);}

    public Collection<String> findNamed() {
        return repository.findNamed();
    }

    public Collection<String> findNamedNotLover() {return repository.findNamedNotLover();}
}
