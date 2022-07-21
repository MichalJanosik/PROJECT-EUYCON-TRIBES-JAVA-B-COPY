package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.ErrorDTO;
import com.example.projecteucyonjavatribesb.model.DTO.OkMessageDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.model.Location;
import com.example.projecteucyonjavatribesb.model.DTO.RequestDTO;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;
    private final KingdomRepository kingdomRepository;
    /**
     * It creates a new location for a kingdom if the coordinates are not taken and are within the valid range (0-99)
     *
     * @param requestDTO
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> createLocation(RequestDTO requestDTO){
        if (locationRepository.existsByCoordinateXAndAndCoordinateY(requestDTO.getCoordinateX(), requestDTO.getCoordinateY())){
            return ResponseEntity.status(400).body(new ErrorDTO("Given coordinates are already taken!\""));
        } else if (requestDTO.getCoordinateX()>99 || requestDTO.getCoordinateX()<0 ||
                requestDTO.getCoordinateY()>99 || requestDTO.getCoordinateY()<0){
           return ResponseEntity.status(400).body(new ErrorDTO("One or both coordinates are out of valid range (0-99)."));
        } else {
            try {
                Location location = new Location(requestDTO.getCoordinateX(),requestDTO.getCoordinateY());
                Optional<Kingdom> kingdom = kingdomRepository.findById(requestDTO.getKingdomId());
                if (kingdom.get().getLocation()!=null){
                    return ResponseEntity.status(400).body(new ErrorDTO("This kingdom already have location"));
                }
                kingdom.get().setLocation(location);
                location.setKingdom(kingdom.get());
                locationRepository.save(location);
                kingdomRepository.save(kingdom.get());
                return ResponseEntity.ok().body(new OkMessageDTO("ok"));
            } catch (Exception e){
                return ResponseEntity.status(400).body(new ErrorDTO("This kingdom does not exist"));
            }
        }
    }
}
