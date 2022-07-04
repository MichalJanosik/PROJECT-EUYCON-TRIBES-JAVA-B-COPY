package com.example.projecteucyonjavatribesb.controller;

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

    @PostMapping("/kingdoms/{id}/buildings")
    public ResponseEntity<Object> addBuilding(@PathVariable("id") Long id, @RequestBody String type){
        Optional<Kingdom> kingdom = kingdomRepository.findById(id);
        return buildingsService.addBuildingMethod(kingdom.get(),type);
    }
}
