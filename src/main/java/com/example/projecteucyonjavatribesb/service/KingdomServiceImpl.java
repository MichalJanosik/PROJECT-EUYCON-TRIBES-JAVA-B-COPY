package com.example.projecteucyonjavatribesb.service;


import com.example.projecteucyonjavatribesb.model.DTO.KingdomDTO;
import com.example.projecteucyonjavatribesb.model.DTO.KingdomNameDTO;
import com.example.projecteucyonjavatribesb.model.DTO.LocationDTO;

import com.example.projecteucyonjavatribesb.model.Kingdom;
import com.example.projecteucyonjavatribesb.repository.BuildingsRepository;
import com.example.projecteucyonjavatribesb.repository.KingdomRepository;
import com.example.projecteucyonjavatribesb.repository.ResourcesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.stream.Collectors;
>>>>>>> b4f47ed03aaaf6328bdc8cc9b3575b23bb4a2da9

@Service
@AllArgsConstructor
public class KingdomServiceImpl implements KingdomService{

    private final KingdomRepository kingdomRepository;
    private final BuildingsRepository buildingsRepository;
    private final ResourcesRepository resourcesRepository;

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
                .build();
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
                new LocationDTO(kingdom.getLocation())
        );
    }

    public List<Kingdom> findAllKingdoms() {
        return kingdomRepository.findAll();
    }
}
