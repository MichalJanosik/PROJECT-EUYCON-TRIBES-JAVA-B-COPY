package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.filter.JwtRequestFilter;
import com.example.projecteucyonjavatribesb.model.DTO.*;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.service.*;

import com.example.projecteucyonjavatribesb.service.*;
import com.example.projecteucyonjavatribesb.service.BuildingsService;
import com.example.projecteucyonjavatribesb.service.KingdomService;
import com.example.projecteucyonjavatribesb.service.PlayerAuthorizationService;
import com.example.projecteucyonjavatribesb.service.ResourcesService;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class KingdomController {

    private final PlayerAuthorizationService playerAuthorizationService;
    private final BuildingsService buildingsService;
    private final KingdomService kingdomService;
    private final ResourcesService resourcesService;
    private final TroopsService troopsService;
    private final BuildingsServiceImpl buildingsServiceimp;

    @PostMapping("/kingdoms/{id}/buildings")
    public ResponseEntity<Object> addBuilding(@PathVariable("id") Long id,
                                              @RequestBody BuildingRequestDTO type,
                                              @RequestHeader(value = "Authorization") String token) throws HttpMessageNotReadableException {
        try {

            if(Objects.nonNull(type) && type.getType()!=null && (type.getType().equals("farm") || type.getType().equals("mine")||
                    type.getType().equals("walls")
                    || type.getType().equals("barracks") || type.getType().equals("academy")) ){
                if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id) || token.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                            body(new ErrorDTO("This kingdom does not belong to authenticated player"));
                } else {
                    return buildingsServiceimp.addBuildingMethod(id, type);
                }
            } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new ErrorDTO("Invalid string"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new ErrorDTO("Invalid string format"));
        }

    }

    @PostMapping("/auth")
    public ResponseEntity<?> getKingdomDetailsFromToken(@RequestHeader(value = "Authorization") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Invalid token!"));
        } else {
            Kingdom kingdomPreview = playerAuthorizationService.getKingdomPreviewFromUsername(JwtRequestFilter.username);
            return ResponseEntity.status(200).body(new KingdomPreviewDTO(kingdomPreview));
        }
    }

    @GetMapping("/kingdoms/{id}/resources")
    public ResponseEntity<?> getKingdomsResources(@PathVariable("id") Long kingdomId,
                                                  @RequestHeader("authorization") String token) {

        if (Objects.nonNull(kingdomId) && Objects.nonNull(token) && !token.isBlank()) {
            if (playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, kingdomId)) {
                resourcesService.generateResources(kingdomId);
                return ResponseEntity.ok().body(resourcesService.getKingdomResources(kingdomId));
            } else {
                throw new RuntimeException("This kingdom does not belong to authenticated player!");
            }
        } else {
            throw new RuntimeException("Player not authorized!");
        }
    }

    @GetMapping("/kingdoms/{id}/buildings")
    public ResponseEntity<Object> getKingdomBuildings(@PathVariable(required = false) Long id,
                                                      @RequestHeader(value = "Authorization") String token) {
        if (kingdomService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("This kingdom does not exists!"));
        } else if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id) || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorDTO("This kingdom does not belong to authenticated player!"));
            }

            KingdomBuildingsDTO kingdomBuildingsDTO = buildingsService.makeKingdomBuildingsDTO(id);
            return ResponseEntity.status(HttpStatus.OK).body(kingdomBuildingsDTO);

        }

        @PutMapping("/kingdoms/{kingdomId}/buildings/{buildingId}")
        public ResponseEntity<Object> upgradeBuildings (@PathVariable(required = false) Long kingdomId,
                @PathVariable(required = false) Long buildingId,
                @RequestHeader(value = "Authorization") String token){
            if (kingdomService.findById(kingdomId).isEmpty()) {
                return ResponseEntity.status(400)
                        .body(new ErrorDTO("This kingdom does not exists!"));
            } else if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, kingdomId) || token.isEmpty()) {
                return ResponseEntity.status(401)
                        .body(new ErrorDTO("This kingdom does not belong to authenticated player!"));

            } else if (buildingsService.findBuildingsByIdAndKingdom(buildingId, kingdomService.findKingdomById(kingdomId)).isEmpty()) {
                return ResponseEntity.status(400)
                        .body(new ErrorDTO("This building does not exists!"));
            } else if (!buildingsService.isReadyForUpgrade(kingdomId, buildingId)) {
                return ResponseEntity.status(400)
                        .body(new ErrorDTO("Building is not ready for reconstruction!"));

            } else if (!buildingsService.enoughResources(kingdomId, buildingId)) {
                return ResponseEntity.status(400)
                        .body(new ErrorDTO("You don't have enough gold to upgrade that!"));
            }
            buildingsService.upgradeBuilding(kingdomId, buildingId);
            BuildingDTO buildingDTO = buildingsService.makeBuildingsDTO(buildingId);
            return ResponseEntity.status(200)
                    .body(buildingDTO);
    }

    @GetMapping("/kingdoms/{id}")
    public ResponseEntity<?> getKingdomDetails(@PathVariable(name = "id") Long id) {
        if (kingdomService.findKingdomById(id) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDTO("This kingdom does not exist."));
        } else if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("This kingdom does not belong to authenticated player!"));
        } else {
            KingdomDetailsDTO kingdomDetails = kingdomService.getKingdomDetailsDTOById(id);
            return ResponseEntity.status(HttpStatus.OK).body(kingdomDetails);
        }
    }

    @GetMapping("/kingdoms/{id}/troops")
    public ResponseEntity<?> getTroopList(@PathVariable(name = "id", required = false) Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDTO("You have not provided an id, please do so! Example: /kingdoms/1/troops"));
        } else if (kingdomService.findKingdomById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("This kingdom does not exist."));
        } else if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("This kingdom does not belong to authenticated player!"));
        } else {
            KingdomDetailsDTO troopsDetails = troopsService.getKingdomTroopsDetailsDTOById(id);
            return ResponseEntity.status(HttpStatus.OK).body(troopsDetails);
        }
}

    @PutMapping("/kingdoms/{id}")
    public ResponseEntity<?> renameKingdom(@PathVariable("id") Long kingdomId,
                                           @RequestBody KingdomNameDTO kingdomNameDTO) {

        if (Objects.nonNull(kingdomNameDTO.getKingdomName()) && !kingdomNameDTO.getKingdomName().isBlank()) {
            if (playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, kingdomId)) {
                kingdomService.renameKingdom(kingdomId, kingdomNameDTO);
                return ResponseEntity.status(HttpStatus.OK).body(kingdomService.getRenamedKingdomDTO(kingdomId));
            } else {
                throw new RuntimeException("This kingdom does not belong to authenticated player!");
            }
        } else {
            throw new RuntimeException("Field kingdomName was empty!");
        }
    }
    @PutMapping("/kingdoms/{id}/troops")
    public ResponseEntity<?> upgradeTroops(@PathVariable("id") Long id,
                                           @RequestBody BuildingRequestDTO troopType,
                                           @RequestHeader(value = "Authorization") String token){
        return null;
    }
}
