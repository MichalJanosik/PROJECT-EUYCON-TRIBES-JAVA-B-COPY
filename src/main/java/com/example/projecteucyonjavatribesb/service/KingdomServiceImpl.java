package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.*;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.ResourcesRepository;
import com.example.projecteucyonjavatribesb.repository.TroopsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KingdomServiceImpl implements KingdomService{

    private final KingdomRepository kingdomRepository;
    private final BuildingsRepository buildingsRepository;
    private final ResourcesRepository resourcesRepository;
    private final TroopsRepository troopsRepository;

    @Override
    public KingdomDetailsDTO getKingdomDetailsDTOById(Long id) {
        Kingdom kingdom = kingdomRepository.getKingdomById(id);
        return extractKingdomDetailsFromKingdom(kingdom);
    }

    @Override
    public Kingdom findKingdomById(Long id) {
        return kingdomRepository.getKingdomById(id);
    }

    private KingdomDetailsDTO extractKingdomDetailsFromKingdom(Kingdom kingdom) {
        return KingdomDetailsDTO
                .builder()
                .kingdom(new KingdomDTO(kingdom))
                .resources(resourcesRepository.findAllByKingdom_Id(kingdom.getId())
                        .stream()
                        .map(ResourcesDTO::new)
                        .collect(Collectors.toList()))
                .buildings(buildingsRepository.findAllByKingdom_Id(kingdom.getId())
                        .stream().map(BuildingDTO::new)
                        .collect(Collectors.toList()))
                .troops(troopsRepository.findAllByKingdom_Id(kingdom.getId())
                        .stream()
                        .map(TroopDTO::new)
                        .collect(Collectors.toList()))
                .build();
    }

    public KingdomDTO getKingdomDTO(Long id) {
        return convertToKingdomDTO(kingdomRepository.findById(id).get());
    }

    private static KingdomDTO convertToKingdomDTO(Kingdom kingdom) {
        return new KingdomDTO(
                kingdom.getId(),
                kingdom.getPlayer().getKingdomName(),
                kingdom.getRuler(),
                kingdom.getPopulation(),
                new LocationDTO(kingdom.getLocation())
        );
    }
}
