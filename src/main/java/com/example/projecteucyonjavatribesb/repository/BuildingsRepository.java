package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingsRepository extends JpaRepository<Buildings,Long> {
    Buildings findByType(String type);
}
