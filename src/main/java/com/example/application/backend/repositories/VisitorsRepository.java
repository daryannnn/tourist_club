package com.example.application.backend.repositories;

import com.example.application.backend.models.Visitors;
import com.example.application.backend.keys.VisitorsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorsRepository extends JpaRepository<Visitors, VisitorsKey> {
}
