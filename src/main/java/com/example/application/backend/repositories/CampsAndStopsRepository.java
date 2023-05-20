package com.example.application.backend.repositories;

import com.example.application.backend.models.CampsAndStops;
import com.example.application.backend.keys.CampsAndStopsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampsAndStopsRepository extends JpaRepository<CampsAndStops, CampsAndStopsKey> {
}
