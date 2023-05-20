package com.example.application.backend.repositories;

import com.example.application.backend.models.HikeDiaries;
import com.example.application.backend.keys.HikeDiariesKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikeDiariesRepository extends JpaRepository<HikeDiaries, HikeDiariesKey> {
}
