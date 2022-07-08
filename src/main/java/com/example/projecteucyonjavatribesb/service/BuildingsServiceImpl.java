package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingRequestDTO;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class BuildingsServiceImpl implements BuildingsService {

    private final BuildingsRepository buildingsRepository;
    private final KingdomRepository kingdomRepository;

    public Class setBuilding(String type, Long id) {
        Kingdom kingdom = kingdomRepository.findById(id).get();
        Buildings buildings = new Buildings();
        buildings.setType(type);
        kingdom.getBuildingList().add(buildings);
        kingdomRepository.save(kingdom);
        buildings.setKingdom(kingdom);
        buildingsRepository.save(buildings);
        return buildings.getClass();
    }

    public ResponseEntity<Object> addBuildingMethod(Long id, BuildingRequestDTO type) {
        long count = kingdomRepository.findById(id).get().getBuildingList().stream()
                .filter(c -> type.getType().equalsIgnoreCase(c.getType()))
                .count();
        if (kingdomRepository.findById(id).get().getBuildingList()
                .stream().anyMatch(x -> x.getType().equalsIgnoreCase("townhall"))) {
            if (type.getType().equalsIgnoreCase("farm") && count < 5) {
                setBuilding(type.getType(), id);
                //TODO: create dto for response
                return ResponseEntity.ok().body("ok");
            } else if (type.getType().equalsIgnoreCase("mine") && count < 3) {
                setBuilding(type.getType(), id);
                //TODO: create dto for response
                return ResponseEntity.ok().body("there must be DTO");
            } else if (type.getType().equalsIgnoreCase("barracks") && count < 1) {
                setBuilding(type.getType(), id);
                //TODO: create dto for response
                return ResponseEntity.ok().body("there must be DTO");
            } else if (type.getType().equalsIgnoreCase("walls") && count < 1) {
                setBuilding(type.getType(), id);
                //TODO: create dto for response
                return ResponseEntity.ok().body("there must be DTO");
            } else if (type.getType().equalsIgnoreCase("academy") && count < 1) {
                setBuilding(type.getType(), id);
                //TODO: create dto for response
                return ResponseEntity.ok().body("there must be DTO but now is ok");
            } else return ResponseEntity.status(400).body("error , too much buildings");
        } else return ResponseEntity.status(400).body("no townhall");
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
