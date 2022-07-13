package com.example.projecteucyonjavatribesb.repository;

import com.example.projecteucyonjavatribesb.model.DTO.TroopDTO;
import com.example.projecteucyonjavatribesb.model.Troops;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TroopsRepository extends JpaRepository<Troops, Long> {
    List<Troops> findAllByKingdom_Id(Long id);
}
