package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public KingdomDTO renameKingdom(KingdomNameDTO kingdomNameDTO, Long id) {
        Kingdom kingdom = kingdomRepository.findById(id).get();
        kingdom.getPlayer().setKingdomName(kingdomNameDTO.getKingdomName());
        kingdomRepository.save(kingdom);
        return getRenamedKingdomDTO(kingdom);
    }

    private KingdomDTO getRenamedKingdomDTO(Kingdom kingdom) {
        Long kingdomId = kingdom.getId();
        String kingdomName = kingdom.getPlayer().getKingdomName();
        KingdomDTO kingdomDTO = KingdomDTO.builder()
                .kingdomId(kingdomId)
                .kingdomName(kingdomName)
                .build();
        return kingdomDTO;
    }

    private static KingdomDTO convertToKingdomDTO(Kingdom kingdom) {
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
}
