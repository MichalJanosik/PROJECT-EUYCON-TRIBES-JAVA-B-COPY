package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.filter.JwtRequestFilter;
import com.example.projecteucyonjavatribesb.model.DTO.*;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.service.BuildingsService;
import com.example.projecteucyonjavatribesb.service.KingdomService;
import com.example.projecteucyonjavatribesb.service.PlayerAuthorizationService;
import com.example.projecteucyonjavatribesb.service.ResourcesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    //    @GetMapping("/kingdoms/{id}")
//    public ResponseEntity<?> getKingdomOverview(@PathVariable(name = "id") Long id,
//                                                @RequestHeader(value = "Authorization") String token) {
//        if (kingdomService.findKingdomById(id) == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ErrorDTO("This kingdom does not exist."));
//        } else if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id) || token.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new ErrorDTO("This kingdom does not belong to authenticated player!"));
//        } else {
//            KingdomOverviewDTO kingdomOverview = kingdomService.getKingdomOverviewDTOById(id);
//            return ResponseEntity.status(HttpStatus.OK).body(kingdomOverview);
//        }
//    }

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

    @PutMapping("/kingdoms/{id}")
    public ResponseEntity<?> renameKingdom(@PathVariable("id") Long kingdomId,
                                           @RequestBody KingdomNameDTO kingdomNameDTO) {

        if (Objects.nonNull(kingdomNameDTO.getKingdomName())) {
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
}
