package com.example.application.backend.repositories;

import com.example.application.backend.models.TouristsToGroups;
import com.example.application.backend.keys.TouristsToGroupsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristsToGroupsRepository extends JpaRepository<TouristsToGroups, TouristsToGroupsKey> {
}
