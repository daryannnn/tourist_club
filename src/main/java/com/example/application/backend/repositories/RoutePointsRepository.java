package com.example.application.backend.repositories;

import com.example.application.backend.models.RoutePoints;
import com.example.application.backend.keys.RoutePointsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutePointsRepository extends JpaRepository<RoutePoints, RoutePointsKey> {
}
