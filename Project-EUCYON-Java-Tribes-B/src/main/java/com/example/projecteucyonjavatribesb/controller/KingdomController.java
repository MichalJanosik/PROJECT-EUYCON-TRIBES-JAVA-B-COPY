package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomOverviewDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomPreviewDTO;
import com.example.projecteucyonjavatribesb.model.DTO.PlayerDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.service.KingdomService;
import com.example.projecteucyonjavatribesb.service.PlayerAuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class KingdomController {

    private final PlayerAuthorizationService playerAuthorizationService;
    private final KingdomService kingdomService;

    @PostMapping("/auth")
    public ResponseEntity<?> getKingdomDetailsFromToken(@RequestHeader(value = "Authorization") String token){
        if (token.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Invalid token!"));
        } else {
            Kingdom kingdomPreview = playerAuthorizationService.getKingdomPreviewFromUsername(playerAuthorizationService.getUsernameFromToken(token));
            return ResponseEntity.status(200).body(new KingdomPreviewDTO(kingdomPreview.getRuler(),
                                                    kingdomPreview.getId(),
                                                    kingdomPreview.getPlayer().getKingdomName()));
        }

    }

    @GetMapping("/kingdoms/{id}")
    public ResponseEntity<?> getKingdomDetails(@PathVariable(name = "id") Long id, @RequestHeader(value = "Authorization") String token){
        if (!playerAuthorizationService.playerOwnsKingdom(id, playerAuthorizationService.getUsernameFromToken(token))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO("This kingdom is not a kingdom of this player!"));
        } else {
            KingdomOverviewDTO kingdomOverviewDTO = kingdomService.getKingdomOverviewById(id);
            return ResponseEntity.status(HttpStatus.OK).body(kingdomOverviewDTO);
        }
    }
}
