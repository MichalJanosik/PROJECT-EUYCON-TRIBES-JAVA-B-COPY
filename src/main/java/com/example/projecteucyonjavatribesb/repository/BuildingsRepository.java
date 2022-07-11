package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BuildingsRepository extends JpaRepository<Buildings, Long> {
    List<Buildings> findAllByKingdom(Kingdom kingdom);
}
