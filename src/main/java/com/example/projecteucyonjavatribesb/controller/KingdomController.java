package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.filter.JwtRequestFilter;
import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomBuildingsDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomPreviewDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
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
    private final KingdomService kingdomService;
    private final KingdomRepository kingdomRepository;
    private final BuildingsRepository buildingsRepository;
    private final BuildingsService buildingsService;
    private final ResourcesService resourcesService;

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
    public ResponseEntity<?> getKindomsResources(@PathVariable("id") Long id,
                                                 @RequestHeader("authorisation") String token
            ) {
//        if (Objects.isNull(id)) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(new ErrorDTO("Invalid id of kingdom!"));
//        } else if (pla) {
//
//        }
//        return ResponseEntity.status(HttpStatus.OK).body();
        if (Objects.nonNull(id)) {
            if (playerAuthorizationService.playerOwnsKingdom(JwtRequestFilter.username, id)
                    && !token.isBlank()
            ) {
                return ResponseEntity.ok().body(resourcesService.getKingdomResources(id));
            } else {
                throw new RuntimeException("This kingdom does not belong to authenticated player!");
            }
        } else {
            throw new RuntimeException("Kingdom ID must be entered!");
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


}