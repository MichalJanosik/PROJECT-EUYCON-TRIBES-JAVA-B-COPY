package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Building;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findAllByKingdomId(Long id);
}
