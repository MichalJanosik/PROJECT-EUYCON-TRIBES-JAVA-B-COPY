package com.example.projecteucyonjavatribesb.service;

import com.example.projecteucyonjavatribesb.model.DTO.*;
import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.BuildingRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.ResourcesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KingdomServiceImpl implements KingdomService{

    private final KingdomRepository kingdomRepository;
    private final BuildingRepository buildingRepository;
    private final ResourcesRepository resourcesRepository;

    @Override
    public KingdomDetailsDTO getKingdomDetailsDTOById(Long id) {
        Kingdom kingdom = kingdomRepository.findKingdomById(id);
        return extractKingdomDetailsFromKingdom(kingdom);
    }

    @Override
    public Kingdom findKingdomById(Long id) {
        return kingdomRepository.findKingdomById(id);
    }

    private KingdomDetailsDTO extractKingdomDetailsFromKingdom(Kingdom kingdom) {
        return KingdomDetailsDTO
                .builder()
                .kingdom(new KingdomDTO(kingdom))
                .resources(resourcesRepository.findAllByKingdomId(kingdom.getId())
                        .stream()
                        .map(ResourcesDTO::new)
                        .collect(Collectors.toList()))
                .buildings(buildingRepository.findAllByKingdomId(kingdom.getId())
                        .stream().map(BuildingDTO::new)
                        .collect(Collectors.toList()))
                .build();
    }
}
