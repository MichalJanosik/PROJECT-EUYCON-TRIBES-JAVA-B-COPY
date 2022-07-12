package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.Utility.BuildingAttributeUtility;
import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Resources;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuildingsServiceImpl implements BuildingsService {
    private final KingdomRepository kingdomRepository;
    private final BuildingsRepository buildingsRepository;
    private final ResourcesService resourcesService;
    private final ResourcesRepository resourcesRepository;

    @Override
    public KingdomBuildingsDTO makeKingdomBuildingsDTO(Long id) {
        Kingdom kingdom = kingdomRepository.getKingdomById(id);
        List<BuildingDTO> listOfBuildings = new ArrayList<>();
        KingdomDTO kingdomDTO = new KingdomDTO(id,
                kingdom.getPlayer().getKingdomName(),
                kingdom.getRuler(),
                kingdom.getPopulation(),
                new LocationDTO(kingdom.getLocation().getCoordinateX(), kingdom.getLocation().getCoordinateY()));

        for (int i = 0; i < kingdom.getBuildingList().size(); i++) {
            listOfBuildings.add(new BuildingDTO(kingdom.getBuildingList().get(i).getId(),
                    kingdom.getBuildingList().get(i).getType(),
                    kingdom.getBuildingList().get(i).getLevel(),
                    kingdom.getBuildingList().get(i).getStartedAt(),
                    kingdom.getBuildingList().get(i).getFinishedAt()
            ));
        }
        return new KingdomBuildingsDTO(kingdomDTO, listOfBuildings);
    }

    @Override
    public Optional<Buildings> findById(Long id) {
        return buildingsRepository.findById(id);
    }

    @Override
    public Boolean enoughResources(Long kingdomId, Long buildingId) {
        List<Resources> listOfResources = resourcesService.getResourcesByKingdomId(kingdomId);
        Integer amount = 0;
        for (Resources listOfResource : listOfResources) {
            if (Objects.equals(listOfResource.getType(), "gold")) {
                amount = listOfResource.getAmount();
            }
        }
        BuildingAttributeUtility buildingUtilities = new BuildingAttributeUtility();
        String typeOfBuilding = buildingsRepository.findBuildingById(buildingId).getType();

        return amount >= buildingUtilities.getCosts().get(typeOfBuilding) *
                (buildingsRepository.findBuildingById(buildingId).getLevel() + 1);
    }


    @Override
    public Optional<Buildings> findBuildingsByIdAndKingdom(long id, Kingdom kingdom) {
        return buildingsRepository.findBuildingsByIdAndKingdom(id, kingdom);
    }

    @Override
    public void upgradeBuilding(Long kingdomId, Long buildingId) {
        BuildingAttributeUtility buildingUtilities = new BuildingAttributeUtility();
        String typeOfBuilding = buildingsRepository.findBuildingById(buildingId).getType();
        // decrease resource by price of upgrade
        resourcesService.useResource(resourcesRepository.findByKingdomAndType
                        (kingdomRepository.findKingdomById(kingdomId), "gold"),
                buildingUtilities.getCosts().get(typeOfBuilding) *
                        (buildingsRepository.findBuildingById(buildingId).getLevel() + 1));

        //level up
        Buildings building = buildingsRepository.getReferenceById(buildingId);
        // new process of reconstruction, higher level of building = longer time for reconstruction
        // level 1 = 1h, other levels = 1h * (next level of building)
        building.setStartedAt(System.currentTimeMillis());
        building.setFinishedAt(System.currentTimeMillis() + (buildingUtilities.getTime().get(building.getType()) * (building.getLevel() + 1)));
        building.setLevel(building.getLevel() + 1);
        buildingsRepository.save(building);

    }

    @Override
    public boolean isReadyForUpgrade(Long kingdomId, Long buildingId) {
        return buildingsRepository.findBuildingsByKingdomAndId(kingdomRepository.findKingdomById(kingdomId),
                buildingId).getFinishedAt() < System.currentTimeMillis();
    }

    @Override
    public BuildingDTO makeBuildingsDTO(Long buildingId) {
        return new BuildingDTO(buildingsRepository.findBuildingById(buildingId).getId(),
                buildingsRepository.findBuildingById(buildingId).getType(),
                buildingsRepository.findBuildingById(buildingId).getLevel(),
                buildingsRepository.findBuildingById(buildingId).getStartedAt(),
                buildingsRepository.findBuildingById(buildingId).getFinishedAt()
        );
    }


}
