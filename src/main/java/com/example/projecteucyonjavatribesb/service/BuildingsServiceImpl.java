package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BuildingsServiceImpl implements BuildingsService {

    private final BuildingsRepository buildingsRepository;
    private final KingdomRepository kingdomRepository;
    //this method put all types of buildings into hashmap
    /**
     * It initializes the HashMap of buildings for a kingdom
     *
     * @param kingdom The kingdom object that you want to initialize the hashmap for.
     */
    public void hashMapInit(Kingdom kingdom){
        kingdom.getBuildingsMap().put("townhall",0);
        kingdom.getBuildingsMap().put("barracks",0);
        kingdom.getBuildingsMap().put("mine",0);
        kingdom.getBuildingsMap().put("farm",0);
        kingdom.getBuildingsMap().put("academy",0);
        kingdom.getBuildingsMap().put("walls",0);
    }

    /**
     * This function adds a building to the kingdom, but only if the kingdom has a townhall, and only if the building type
     * is not already at its maximum limit
     *
     * @param kingdom the kingdom object that is being modified
     * @param type String
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> addBuildingMethod(Kingdom kingdom, String type){
        hashMapInit(kingdom);
        if (kingdom.getBuildingsList().contains(buildingsRepository.findByType("townhall"))){
            if (kingdom.getBuildingsMap().get(type)<1){
                Buildings buildings = new Buildings();
                buildings.setType(type);
                kingdom.getBuildingsList().add(buildings);
                kingdom.getBuildingsMap().put(type, kingdom.getBuildingsMap().get(type)+1);
                buildingsRepository.save(buildings);
                kingdomRepository.save(kingdom);
                //TODO: create dto for response
                return ResponseEntity.ok().body("there must be DTO");
            } else if (type.equals("farm") && kingdom.getBuildingsMap().get(type)<5){
                Buildings buildings = new Buildings();
                buildings.setType(type);
                kingdom.getBuildingsList().add(buildings);
                kingdom.getBuildingsMap().put(type, kingdom.getBuildingsMap().get(type)+1);
                buildingsRepository.save(buildings);
                kingdomRepository.save(kingdom);
                //TODO: create dto for response
                return ResponseEntity.ok().body("there must be DTO");
            } else if (type.equals("farm") && kingdom.getBuildingsMap().get(type)<3){
                Buildings buildings = new Buildings();
                buildings.setType(type);
                kingdom.getBuildingsList().add(buildings);
                kingdom.getBuildingsMap().put(type, kingdom.getBuildingsMap().get(type)+1);
                buildingsRepository.save(buildings);
                kingdomRepository.save(kingdom);
                //TODO: create dto for response
                return ResponseEntity.ok().body("there must be DTO");
            } else return ResponseEntity.status(400).body("Maximum limit of this type of building");
        }else {
            return ResponseEntity.status(400).body("Kingdom must have townhall as a first building");
        }
    }
}
