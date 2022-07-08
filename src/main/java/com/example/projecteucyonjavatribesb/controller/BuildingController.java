package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.Buildings;
import com.example.projecteucyonjavatribesb.model.DTO.BuildingRequestDTO;
import com.example.projecteucyonjavatribesb.model.DTO.RequestDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.service.BuildingsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BuildingController {

    private final BuildingsServiceImpl buildingsService;
    private final KingdomRepository kingdomRepository;

    /**
     * This function takes in a kingdom id and a building type, and adds a building of that type to the kingdom
     *
     * @param id The id of the kingdom you want to add a building to.
     * @param type String
     * @return ResponseEntity<Object>
     */
    @PostMapping("/kingdoms/{id}/buildings")
    public ResponseEntity<Object> addBuilding(@PathVariable("id") Long id, @RequestBody BuildingRequestDTO type){
        return buildingsService.addBuildingMethod(id,type);
    }
}
