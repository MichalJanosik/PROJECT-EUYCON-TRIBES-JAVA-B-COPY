package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;

import java.util.Optional;

public interface BuildingsService {
    KingdomBuildingsDTO makeKingdomBuildingsDTO(Long id);
    Optional<Buildings> findById(Long id);
    Boolean enoughResources(Long kingdomId, Long buildingId);
    Optional<Buildings> findBuildingsByIdAndKingdom(long id, Kingdom kingdom);

    void upgradeBuilding( Long kingdomId, Long buildingId);

    BuildingDTO makeBuildingsDTO(Long buildingId);

    boolean isReadyForUpgrade(Long kingdomId, Long buildingId);
}
