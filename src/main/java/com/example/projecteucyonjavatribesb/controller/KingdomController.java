package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.filter.JwtRequestFilter;
import com.example.projecteucyonjavatribesb.model.DTO.*;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.service.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class KingdomController {

    private final PlayerAuthorizationService playerAuthorizationService;
    private final KingdomService kingdomService;
    private final KingdomRepository kingdomRepository;
    private final BuildingsRepository buildingsRepository;
    private final BuildingsService buildingsService;
    private final ResourcesService resourcesService;

    private final BuildingsServiceImpl buildingsServiceimp;
    @PostMapping("/kingdoms/{id}/buildings")
    public ResponseEntity<Object> addBuilding(@PathVariable("id") Long id,
                                              @RequestBody BuildingRequestDTO type,
                                              @RequestHeader(value = "Authorization") String token) {
        if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id) || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
            body(new ErrorDTO("This kingdom does not belong to authenticated player"));
        } else {
            return buildingsServiceimp.addBuildingMethod(id, type);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> getKingdomDetailsFromToken(@RequestHeader(value = "Authorization") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Invalid token!"));
        } else {
            Kingdom kingdomPreview = playerAuthorizationService.getKingdomPreviewFromUsername(JwtRequestFilter.username);
            return ResponseEntity.status(200).body(new KingdomPreviewDTO(kingdomPreview.getRuler(),
                    kingdomPreview.getId(),
                    kingdomPreview.getPlayer().getKingdomName()));
        }
    }

    @GetMapping("/kingdoms/{id}/resources")
    public ResponseEntity<?> getKingdomsResources(@PathVariable("id") Long kingdomId,
                                                  @RequestHeader("Authorization") String token) {

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
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Invalid id of kingdom!"));
        } else if (!playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id) || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("This kingdom does not belong to authenticated player!"));
        }

        KingdomBuildingsDTO kingdomBuildingsDTO = buildingsService.makeKingdomBuildingsDTO(id);
        return ResponseEntity.status(HttpStatus.OK).body(kingdomBuildingsDTO);
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
}

