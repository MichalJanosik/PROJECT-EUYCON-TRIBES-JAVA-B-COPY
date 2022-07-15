package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.filter.JwtRequestFilter;
import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomDetailsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomPreviewDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;

import com.example.projecteucyonjavatribesb.service.*;
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
    private final KingdomService kingdomService;
    private final BuildingsService buildingsService;
    private final ResourcesService resourcesService;
    private final TroopsService troopsService;

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
    public ResponseEntity<Object> getKingdomBuildings(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Invalid id of kingdom!"));
        } else if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("This kingdom does not belong to authenticated player!"));
        }

        KingdomBuildingsDTO kingdomBuildingsDTO = buildingsService.makeKingdomBuildingsDTO(id);
        return ResponseEntity.status(HttpStatus.OK).body(kingdomBuildingsDTO);

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
                    .body(new ErrorDTO("Entered Kingdom id not exists!"));
        } else if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("This kingdom does not belong to authenticated player"));
        } else {
            KingdomDetailsDTO troopsDetails = troopsService.getKingdomTroopsDetailsDTOById(id);
            return ResponseEntity.status(HttpStatus.OK).body(troopsDetails);
        }
    }
}

