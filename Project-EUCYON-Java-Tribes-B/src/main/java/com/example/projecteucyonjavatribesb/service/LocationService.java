package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.DTO.RequestDTO;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.LocationRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final KingdomRepository kingdomRepository;
    public ResponseEntity<String> createLocation(RequestDTO requestDTO){
        if (locationRepository.existsByCoordinateXAndAndCoordinateY(requestDTO.getCoordinateX(), requestDTO.getCoordinateY())){
            return ResponseEntity.status(400).body("Given coordinates are already taken!\"");
        } else if (requestDTO.getCoordinateX()>99 || requestDTO.getCoordinateX()<0 ||
                requestDTO.getCoordinateY()>99 || requestDTO.getCoordinateY()<0){
           return ResponseEntity.status(400).body("One or both coordinates are out of valid range (0-99).");
        } else {
            Location location = new Location(requestDTO.getCoordinateX(),requestDTO.getCoordinateY());
            Optional<Kingdom> kingdom = kingdomRepository.findById(requestDTO.getKingdomId());
            kingdom.get().setLocation(location);
            locationRepository.save(location);
            kingdomRepository.save(kingdom.get());
           return ResponseEntity.ok().body("ok");
        }
    }
}
