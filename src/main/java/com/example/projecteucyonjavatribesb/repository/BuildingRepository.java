package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Buildings, Long> {
    List<Buildings> findAllByKingdomId(Long id);
}
