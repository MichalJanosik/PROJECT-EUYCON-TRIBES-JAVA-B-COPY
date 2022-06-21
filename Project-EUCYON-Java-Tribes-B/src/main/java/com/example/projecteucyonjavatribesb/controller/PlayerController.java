package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.RequestDTO;
import com.example.projecteucyonjavatribesb.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PlayerController {
    private final LocationService locationService;

    @PutMapping("/registration")
    public ResponseEntity<String> setLocation(@RequestBody RequestDTO requestDTO){
      return locationService.createLocation(requestDTO);
    }
}
