package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KingdomServiceImpl implements KingdomService {

    private final KingdomRepository kingdomRepository;

    @Override
    public Kingdom findKingdomById(Long id) {
        return kingdomRepository.findById(id).get();
    }

    public KingdomDTO getKingdomDTO(Long id) {
        return convertToKingdomDTO(kingdomRepository.findById(id).get());
    }

    @Override
    public void renameKingdom(Long kingdomId, KingdomNameDTO kingdomNameDTO) {
        Kingdom kingdom = kingdomRepository.findById(kingdomId).get();
        kingdom.getPlayer().setKingdomName(kingdomNameDTO.getKingdomName());
        kingdomRepository.save(kingdom);
    }

    @Override
    public KingdomDTO getRenamedKingdomDTO(Long kingdomId) {
        Kingdom kingdom = kingdomRepository.findById(kingdomId).get();
        return KingdomDTO.builder()
                .kingdomId(kingdom.getId())
                .kingdomName(kingdom.getPlayer().getKingdomName())
                .build();
    }

    private KingdomDTO convertToKingdomDTO(Kingdom kingdom) {
        return new KingdomDTO(
                kingdom.getId(),
                kingdom.getPlayer().getKingdomName(),
                kingdom.getRuler(),
                kingdom.getPopulation(),
                new LocationDTO(
                        kingdom.getLocation().getCoordinateX(),
                        kingdom.getLocation().getCoordinateY()
                )
        );
    }

    public List<Kingdom> findAllKingdoms() {
        return kingdomRepository.findAll();
    }
}
