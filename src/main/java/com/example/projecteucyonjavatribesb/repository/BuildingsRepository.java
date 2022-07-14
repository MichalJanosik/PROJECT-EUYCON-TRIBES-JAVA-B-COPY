package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingsRepository extends JpaRepository<Buildings,Long> {
    Optional<Buildings> findByType(String type);

    List<Buildings> findAllByKingdom(Kingdom kingdom);

    List<Buildings> findAllByKingdom_Id(Long id);
}
