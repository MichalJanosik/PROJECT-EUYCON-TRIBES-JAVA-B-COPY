package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingRequestDTO;

import com.example.projecteucyonjavatribesb.model.DTO.BuildingDTO;
import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Resources;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.utility.BuildingAttributeUtility;
import com.example.projecteucyonjavatribesb.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
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

    //TODO: buildingDTO dont have correct fields, missing id and times

    /**
     * The function takes in a type of building and a kingdom id, and returns a buildingDTO object
     *
     * @param type the type of building you want to build
     * @param id   The id of the kingdom you want to add the building to.
     * @return BuildingDTO
     */
    public BuildingDTO setBuilding(String type, Long id) {
        BuildingAttributeUtility buildingAttributeUtility1 = new BuildingAttributeUtility();
        Kingdom kingdom = kingdomRepository.findById(id).get();
        Buildings buildings = new Buildings();
        buildings.setType(type);
        buildings.setLevel(1);
        buildings.setStartedAt(System.currentTimeMillis());
        buildings.setFinishedAt(System.currentTimeMillis() + (buildingAttributeUtility1.getTime().get(buildings.getType())));
        kingdom.getBuildingList().add(buildings);
        kingdomRepository.save(kingdom);
        buildings.setKingdom(kingdom);
        buildingsRepository.save(buildings);
        BuildingDTO buildingDTO = new BuildingDTO(buildings);
        return buildingDTO;
    }

    /**
     * It checks if the building type is empty, if it is, it returns an error. If it isn't, it checks if the building type
     * is already in the kingdom, if it is, it returns an error. If it isn't, it checks if the kingdom has a townhall, if
     * it doesn't, it returns an error. If it does, it checks if the building type is farm, mine, barracks, walls or
     * academy, if it isn't, it returns an error. If it is, it checks if the building type is already in the kingdom, if it
     * is, it returns an error. If it isn't, it checks if the kingdom has enough gold to build the building, if it doesn't,
     * it returns an error. If it does, it uses the gold to build the building
     *
     * @param id   the id of the kingdom
     * @param type the type of the building you want to build
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> addBuildingMethod(Long id, BuildingRequestDTO type) {
        BuildingAttributeUtility buildingAttributeUtility1 = new BuildingAttributeUtility();
        if (type.getType().isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorDTO("type required"));
        }
        long count = kingdomRepository.findById(id).get().getBuildingList().stream()
                .filter(c -> type.getType().equalsIgnoreCase(c.getType()))
                .count();
        if (kingdomRepository.findById(id).get().getBuildingList()
                .stream().anyMatch(x -> x.getType().equalsIgnoreCase("Townhall")) || kingdomRepository.findById(id).get().getBuildingList()
                .stream().anyMatch(x -> x.getType().equalsIgnoreCase("town hall"))) {
            if (type.getType().equalsIgnoreCase("farm") && count < 5) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.costs.get(type.getType()));
                    return ResponseEntity.ok().body(setBuilding(type.getType(), id));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
            } else if (type.getType().equalsIgnoreCase("mine") && count < 3) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()));
                    return ResponseEntity.ok().body(setBuilding(type.getType(), id));

                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
            } else if (type.getType().equalsIgnoreCase("barracks") && count < 1) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()));
                    return ResponseEntity.ok().body(setBuilding(type.getType(), id));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
            } else if (type.getType().equalsIgnoreCase("walls") && count < 1) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()));
                    return ResponseEntity.ok().body(setBuilding(type.getType(), id));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
            } else if (type.getType().equalsIgnoreCase("academy") && count < 1) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), buildingAttributeUtility1.getCosts().get(type.getType()));
                    return ResponseEntity.ok().body(setBuilding(type.getType(), id));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
            } else return ResponseEntity.status(400).body(new ErrorDTO("error , too much buildings of this type"));
        } else return ResponseEntity.status(400).body(new ErrorDTO("There is no Townhall in kingdom"));
    }

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