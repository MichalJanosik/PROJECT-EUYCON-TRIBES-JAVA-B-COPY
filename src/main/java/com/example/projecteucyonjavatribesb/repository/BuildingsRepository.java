package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BatchUpdateUtils;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingsRepository extends JpaRepository<Buildings, Long> {
    List<Buildings> findAllByKingdom(Kingdom kingdom);
    Optional<Buildings> findById(Long id);
    Buildings findBuildingById(Long Id);
    Optional<Buildings> findBuildingsByIdAndKingdom(long id, Kingdom kingdom);
    Buildings findBuildingsByKingdomAndId(Kingdom kingdom, Long id);

    List<Buildings> findAllByKingdom_Id(Long id);
}
