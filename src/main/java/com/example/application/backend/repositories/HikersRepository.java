package com.example.application.backend.repositories;

import com.example.application.backend.models.Hikers;
import com.example.application.backend.keys.HikersKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikersRepository extends JpaRepository<Hikers, HikersKey> {
}
