package com.example.application.backend.repositories;

import com.example.application.backend.models.Competitors;
import com.example.application.backend.keys.CompetitorsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorsRepository extends JpaRepository<Competitors, CompetitorsKey> {
}
