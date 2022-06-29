package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.RequestDTO;
import com.example.projecteucyonjavatribesb.service.LocationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.DTO.PlayerDTO;
import com.example.projecteucyonjavatribesb.model.Player;
import com.example.projecteucyonjavatribesb.repository.PlayerRepository;
import com.example.projecteucyonjavatribesb.service.PlayerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PlayerController {
    private final LocationServiceImpl locationServiceImpl;
    private final PlayerServiceImpl playerService;
    private final PlayerRepository playerRepository;

    @PutMapping("/locationRegister")
    public ResponseEntity<Object> setLocation(@RequestBody RequestDTO requestDTO){
      return locationServiceImpl.createLocation(requestDTO);
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody Player player) {
        if (playerService.checkIfUsernameAlreadyExist(player.getUsername())) {
            return ResponseEntity.status(400).body(new ErrorDTO("Username already exists!"));
        }
        if (player.getUsername().isEmpty() || player.getUsername() == null) {
            return ResponseEntity.status(400).body(new ErrorDTO("Username can't be empty!"));
        }
        if (player.getPassword().length() < 8 || player.getPassword() == null) {
            return ResponseEntity.status(400).body(new ErrorDTO("Password must be at least 8 characters long"));
        }
        if (player.getKingdomName().isEmpty() || player.getKingdomName() == null) {
            playerService.saveNewPlayerWithDefaultKingdomName(player);
            return ResponseEntity.status(200).body(new PlayerDTO(player.getUsername(), playerRepository.findByUsername(player.getUsername()).getKingdom().getId()));

        }
            playerService.saveNewPlayer(player);
            return ResponseEntity.status(200).body(new PlayerDTO(player.getUsername(),playerRepository.findByUsername(player.getUsername()).getKingdom().getId()));

    }

}
