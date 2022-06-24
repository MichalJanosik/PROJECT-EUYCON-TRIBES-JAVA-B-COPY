package com.example.projecteucyonjavatribesb.controller;

import com.example.projecteucyonjavatribesb.model.DTO.RequestDTO;
import com.example.projecteucyonjavatribesb.service.LocationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PlayerController {
    private final LocationServiceImpl locationServiceImpl;

    @PutMapping("/locationRegister")
    public ResponseEntity<Object> setLocation(@RequestBody RequestDTO requestDTO){
      return locationServiceImpl.createLocation(requestDTO);
    }
}
