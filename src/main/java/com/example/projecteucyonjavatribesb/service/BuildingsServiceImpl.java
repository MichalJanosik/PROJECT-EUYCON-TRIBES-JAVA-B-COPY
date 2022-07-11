package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingRequestDTO;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingDTO;
import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Resources;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuildingsServiceImpl implements BuildingsService {

    private final BuildingsRepository buildingsRepository;
    private final KingdomRepository kingdomRepository;
    private final ResourcesServiceImpl resourcesService;
    private HashMap<String, Integer> costs = new HashMap<>();
    //TODO: buildingDTO dont have correct fields, missing id and times

    /**
     * This function sets the costs of each building to a specific value
     */
    public void setCosts(){
        // Setting the costs of the buildings.
        costs.put("farm",30);
        costs.put("mine",30);
        costs.put("academy",50);
        costs.put("barracks",60);
        costs.put("walls",60);
    }

    /**
     * The function takes in a type of building and a kingdom id, and returns a buildingDTO object
     *
     * @param type the type of building you want to build
     * @param id The id of the kingdom you want to add the building to.
     * @return BuildingDTO
     */
    public BuildingDTO setBuilding(String type, Long id) {
        Kingdom kingdom = kingdomRepository.findById(id).get();
        Buildings buildings = new Buildings();
        buildings.setType(type);
        kingdom.getBuildingList().add(buildings);
        kingdomRepository.save(kingdom);
        buildings.setKingdom(kingdom);
        buildingsRepository.save(buildings);
        BuildingDTO buildingDTO = new BuildingDTO(buildings.getId(),buildings.getType(), buildings.getLevel());
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
     * @param id the id of the kingdom
     * @param type the type of the building you want to build
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> addBuildingMethod(Long id, BuildingRequestDTO type) {
        if(type.getType().isEmpty()){
            return ResponseEntity.status(400).body(new ErrorDTO("type required"));
        }
        long count = kingdomRepository.findById(id).get().getBuildingList().stream()
                .filter(c -> type.getType().equalsIgnoreCase(c.getType()))
                .count();
        if (kingdomRepository.findById(id).get().getBuildingList()
                .stream().anyMatch(x -> x.getType().equalsIgnoreCase("townhall")) || kingdomRepository.findById(id).get().getBuildingList()
                .stream().anyMatch(x -> x.getType().equalsIgnoreCase("town hall"))) {
            if (type.getType().equalsIgnoreCase("farm") && count < 5) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0),costs.get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), costs.get(type.getType()));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
                return ResponseEntity.ok().body(setBuilding(type.getType(), id));
            } else if (type.getType().equalsIgnoreCase("mine") && count < 3) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0),costs.get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), costs.get(type.getType()));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
                return ResponseEntity.ok().body(setBuilding(type.getType(), id));
            } else if (type.getType().equalsIgnoreCase("barracks") && count < 1) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0),costs.get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), costs.get(type.getType()));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
                return ResponseEntity.ok().body(setBuilding(type.getType(), id));
            } else if (type.getType().equalsIgnoreCase("walls") && count < 1) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0),costs.get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), costs.get(type.getType()));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
                return ResponseEntity.ok().body(setBuilding(type.getType(), id));
            } else if (type.getType().equalsIgnoreCase("academy") && count < 1) {
                if (resourcesService.canBeResourceUsed(resourcesService.getResourcesByKingdomId(id).get(0),costs.get(type.getType()))) {
                    resourcesService.useResource(resourcesService.getResourcesByKingdomId(id).get(0), costs.get(type.getType()));
                } else {
                    return ResponseEntity.status(400).body(new ErrorDTO("You don't have enough gold to build that!"));
                }
                return ResponseEntity.ok().body(setBuilding(type.getType(), id));
            } else return ResponseEntity.status(400).body(new ErrorDTO("error , too much buildings of this type"));
        } else return ResponseEntity.status(400).body(new ErrorDTO("There is no Townhall in kingdom"));
    }

        @Override
        public KingdomBuildingsDTO makeKingdomBuildingsDTO (Long id){
            Kingdom kingdom = kingdomRepository.getKingdomById(id);
            List<BuildingDTO> listOfBuildings = new ArrayList<>();
            for (int i = 0; i < kingdom.getBuildingList().size(); i++) {
                listOfBuildings.add(new BuildingDTO(kingdom.getBuildingList().get(i).getId(),
                        kingdom.getBuildingList().get(i).getType(),
                        kingdom.getBuildingList().get(i).getLevel()));
            }
            KingdomBuildingsDTO kingdomBuildingsDTO = new KingdomBuildingsDTO(
                    id,
                    kingdom.getPlayer().getKingdomName(),
                    kingdom.getRuler(),
                    kingdom.getPopulation(),
                    kingdom.getLocation(),
                    listOfBuildings
            );
            return kingdomBuildingsDTO;
        }
    }
